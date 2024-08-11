package com.example.sample.pager;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.sample.data.api.ApiInterface;
import com.example.sample.data.response.user.DataItem;
import com.example.sample.data.response.user.UserResponse;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, DataItem> {

    private static final int PAGE_SIZE = 10;
    private static final int INITIAL_PAGE = 1;

    private ApiInterface mApiInterface;

    public ItemDataSource(ApiInterface mApiInterface) {
        this.mApiInterface = mApiInterface;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataItem> callback) {
        mApiInterface.getUserPager(INITIAL_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserResponse>() {
                    @Override
                    public void onNext(UserResponse items) {
                        callback.onResult(items.getData(), null, INITIAL_PAGE + 1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Handle error
                    }

                    @Override
                    public void onComplete() {
                        // Handle completion
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataItem> callback) {
        // Not used for initial load
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataItem> callback) {
        mApiInterface.getUserPager(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserResponse>() {
                    @Override
                    public void onNext(UserResponse items) {
                        callback.onResult(items.getData(), params.key + 1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Handle error
                    }

                    @Override
                    public void onComplete() {
                        // Handle completion
                    }
                });
    }
}

