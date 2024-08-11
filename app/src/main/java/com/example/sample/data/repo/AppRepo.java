package com.example.sample.data.repo;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.sample.app.AppConstants;
import com.example.sample.data.api.ApiInterface;
import com.example.sample.data.response.user.DataItem;
import com.example.sample.data.response.userrealm.DataItems;
import com.example.sample.data.response.userrealm.UserResponses;
import com.example.sample.pager.ItemDataSource;
import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.realm.Realm;


public class AppRepo {

    private ApiInterface api;

    public AppRepo(ApiInterface api) {
        this.api = api;
    }


    /*------------------------------------User----------------------------------------------*/
    public Observable<UserResponses> getUser() {
        return api.getUser().subscribeOn(Schedulers.io())
                .map(userResponse -> {
                    if (userResponse == null) {
                        throw new RuntimeException(AppConstants.API_UNKNOWN_FAILURE_MSG);
                    }
                    return userResponse;
                });
    }


    public LiveData<PagedList<DataItem>>getUserPager() {
        DataSource.Factory<Integer, DataItem> dataSourceFactory = new DataSource.Factory<Integer, DataItem>() {
            @Override
            public DataSource<Integer, DataItem> create() {
                return new ItemDataSource(api);
            }
        };

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10) // Number of items loaded at once
                .setEnablePlaceholders(false) // Show placeholders while data is loading
                .build();

        return new LivePagedListBuilder<>(dataSourceFactory, config).build();
    }

    public List<DataItems> getItemsFromRealm() {
        Realm realm = Realm.getDefaultInstance();
        List<DataItems> items = realm.copyFromRealm(realm.where(DataItems.class).findAll());
        realm.close();
        return items;
    }

}
