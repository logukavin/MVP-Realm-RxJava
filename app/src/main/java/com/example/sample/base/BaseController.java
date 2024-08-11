package com.example.sample.base;

public interface BaseController<V extends BaseView> {

    /**
     * This method is used to set the view of an activity
     *
     * @param view is a generic view which is from activity or fragment
     */
    void setView(V view);

    /**
     * This method is used to destroy the view
     */
    void destroyView();

    /**
     * This method is used to destroy an activity or fragment from the Controller
     */
    void destroy();

}
