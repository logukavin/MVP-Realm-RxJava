package com.example.sample.base;


import com.example.sample.app.AppController;
import com.example.sample.data.di.componants.RepositoryComponant;

public abstract class AbstractBaseController<V extends BaseView> implements BaseController<V> {

    protected V view;

    protected abstract void inject(RepositoryComponant repositoryComponant);

    @Override
    public void setView(V view) {
        inject(AppController.getInstance().getRepositoryComponant());
        this.view = view;
    }

    @Override
    public void destroyView() {
        view = null;
    }

    @Override
    public void destroy() {
        destroyView();
    }
}
