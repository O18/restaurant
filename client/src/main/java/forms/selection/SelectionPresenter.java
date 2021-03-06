package forms.selection;

import forms.eventbus.EventBus;
import model.MenuDTO;

import java.util.*;

/**
 * Created by User on 29.01.2017
 */
public class SelectionPresenter {
    private final EventBus eventBus;
    private final MenuSelectionScreen selectionScreen;

    public SelectionPresenter(EventBus eventBus, MenuSelectionScreen selectionScreen){
        this.eventBus = eventBus;
        this.selectionScreen = selectionScreen;
    }

    void getMenuNamesList(){
        eventBus.post(new GetMenuNamesEvent(new GetMenuNamesCallback() {
            @Override
            public void onSuccess(Set<String> menuNames) {
                List<String> menusNamesList = new ArrayList<>();
                menusNamesList.addAll(menuNames);
                menusNamesList.sort(String::compareTo);
                selectionScreen.setMenuList(menusNamesList);
            }

            @Override
            public void onFail(RuntimeException e) {
                selectionScreen.showErrorMessage(e.getMessage());
            }
        }));
    }

    void getMenuByName(String menuName){
        eventBus.post(new GetMenuEvent(menuName, new GetMenuCallback() {
            @Override
            public void onSuccess(MenuDTO menuDTO, String menuName) {
                selectionScreen.openMenu(menuDTO, menuName);
            }

            @Override
            public void onFail(RuntimeException e) {
                selectionScreen.showErrorMessage(e.getMessage());
            }
        }));
    }

    void deleteMenu(String menuName) {
        eventBus.post(new DeleteMenuEvent(menuName, new DeleteMenuCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(RuntimeException e) {
                selectionScreen.showErrorMessage(e.getMessage());
            }
        }));
    }

    void createMenu(String menuName) {
        eventBus.post(new CreateMenuEvent(menuName, new CreateMenuCallback() {
            @Override
            public void onFail(RuntimeException e) {
                selectionScreen.showErrorMessage(e.getMessage());
            }

            @Override
            public void onSuccess(MenuDTO menu, String menuName) {
                selectionScreen.openMenu(menu, menuName);
            }
        }));
    }
}