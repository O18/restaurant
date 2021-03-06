package com.o18.restaurant.events;

import com.o18.restaurant.eventbus.Event;
import com.o18.restaurant.presenter.Callback;

/**
 * Created by 1 on 14.11.2016.
 */
public class SaveMenuEvent implements Event {
    private final String path;
    private final Callback callback;

    public SaveMenuEvent(String path, Callback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }

    public Callback getCallback() {
        return callback;
    }
}
