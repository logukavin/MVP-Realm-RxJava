package com.example.sample.ui.user;

import android.annotation.SuppressLint;

import com.example.sample.base.AbstractBaseController;
import com.example.sample.data.di.componants.RepositoryComponant;
import com.example.sample.data.repo.AppRepo;
import com.example.sample.data.response.userrealm.DataItems;
import com.example.sample.data.response.userrealm.UserResponses;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.realm.Realm;

public class UserController extends AbstractBaseController<UserView> {
    @Inject
    AppRepo appRepo;
    private UserView view;
    private Disposable disposable;

    @Override
    protected void inject(RepositoryComponant repositoryComponant) {
        repositoryComponant.inject(this);
    }

    @Override
    public void setView(UserView view) {
        super.setView(view);
        this.view = view;
    }


    @SuppressLint("CheckResult")
    public void getUser() {
        view.showLoading();
        disposable= appRepo.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError);
    }

    @SuppressLint("SuspiciousIndentation")
    private void handleResponse(UserResponses userResponse) {
        // Handle API response here
        if (view != null) {
            if (userResponse != null)
//                view.OnUserResponse(userResponse);
            saveItemsToRealm(userResponse.getData());
            view.hideLoading();
        }
    }

    private void handleError(Throwable error) {
        // Handle API error here
        if (view != null) {
            view.showError(error.getMessage());
            view.hideLoading();
        }
    }


    @Override
    public void destroyView() {
        super.destroyView();
    }


    @Override
    public void destroy() {
        super.destroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    public void saveItemsToRealm(List<DataItems> items) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realmInstance -> {
            realmInstance.deleteAll();
            realmInstance.insertOrUpdate(items);
        }, () -> {
            realm.close();
        }, error -> {
            realm.close();
            error.printStackTrace();
        });
    }

    public List<DataItems> fetchDataFromRealm() {
        return appRepo.getItemsFromRealm();
    }

}
