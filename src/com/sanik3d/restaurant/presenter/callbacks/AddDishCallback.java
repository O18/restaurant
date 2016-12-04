package com.sanik3d.restaurant.presenter.callbacks;

/**
 * Created by 1 on 29.11.2016.
 */
public interface AddDishCallback {
    void onSuccess();
    void onFailDishAlreadyExists();
    void onFailCategoryDoesNotExist();

    void onFail(RuntimeException e);
}