package forms.creation;

import client.MenuClient;
import client.ServerException;
import forms.eventbus.EventBus;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public class CreationController {
    private final MenuClient client;

    public CreationController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler(CreateMenuEvent.class, this::createMenu);
    }

    private void createMenu(CreateMenuEvent event){
        try{
            client.createMenu(event.getMenuName());
            MenuDTO createdMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(createdMenu, event.getMenuName());
        } catch (ServerException e){
            event.getCallback().onFail(e);
        }
    }
}