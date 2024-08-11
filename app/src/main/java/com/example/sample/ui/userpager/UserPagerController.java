package com.example.sample.ui.userpager;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.sample.base.AbstractBaseController;
import com.example.sample.data.di.componants.RepositoryComponant;
import com.example.sample.data.repo.AppRepo;
import com.example.sample.data.response.user.DataItem;

import javax.inject.Inject;

public class UserPagerController extends AbstractBaseController<UserPagerView> {
    @Inject
    AppRepo appRepo;
    private UserPagerView view;
    private LiveData<PagedList<DataItem>> itemsPagedList;

    @Override
    protected void inject(RepositoryComponant repositoryComponant) {
        repositoryComponant.inject(this);
    }

    @Override
    public void setView(UserPagerView view) {
        super.setView(view);
        this.view = view;
    }


    public void getUserPager() {
        itemsPagedList = appRepo.getUserPager();

    }

    public LiveData<PagedList<DataItem>> getItemsPagedList() {
        return itemsPagedList;
    }
}
