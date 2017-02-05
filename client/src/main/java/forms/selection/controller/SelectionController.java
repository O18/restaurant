package forms.selection.controller;

import client.MenuClient;
import client.ServerException;
import forms.eventbus.EventBus;
import forms.selection.events.DeleteMenuEvent;
import forms.selection.events.GetMenuEvent;
import forms.selection.events.GetMenuNamesEvent;
import model.MenuDTO;

import java.util.Set;

/**
 * Created by User on 29.01.2017
 */
public class SelectionController {
    private final MenuClient client;

    public SelectionController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler(GetMenuNamesEvent.class, this::loadMenuNames);
        eventBus.addHandler(GetMenuEvent.class, this::loadMenu);
        eventBus.addHandler(DeleteMenuEvent.class, this::deleteMenu);
    }

    private void loadMenuNames(GetMenuNamesEvent event){
        try {
            Set<String> menuNames = client.getMenusNames();
            event.getCallback().onSuccess(menuNames);
        } catch (ServerException e){
            event.getCallback().onFail(e);
        }
    }

    private void loadMenu(GetMenuEvent event){
        try{
            MenuDTO menu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(menu, event.getMenuName());
        } catch (ServerException e) {
            event.getCallback().onFail(e);
        }
    }

    private void deleteMenu(DeleteMenuEvent event){
        try {
            client.deleteMenu(event.getMenuName());
            event.getCallback().onSuccess();
        } catch (ServerException e){
            event.getCallback().onFail(e);
        }
    }
}
