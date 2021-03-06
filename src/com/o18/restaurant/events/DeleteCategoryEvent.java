package com.o18.restaurant.events;


import com.o18.restaurant.eventbus.Event;
import com.o18.restaurant.presenter.Callback;


/**
 * Created by 1 on 28.11.2016.
 */
public class DeleteCategoryEvent implements Event {
    private final String nameOfCategory;
    private final Callback callback;
    public DeleteCategoryEvent (String nameOfCategory,Callback callback) {
        this.nameOfCategory = nameOfCategory;
        this.callback = callback;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public Callback getCallback() {
        return callback;
    }
}