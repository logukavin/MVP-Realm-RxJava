package com.example.sample.utils;


import com.example.sample.app.AppConstants;
import com.example.sample.app.AppController;
import com.example.sample.data.exception.ApiHttpException;
import com.example.sample.data.exception.AppException;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import retrofit2.Response;


public class RxJavaUtils {
    public static <T> ObservableTransformer<T, T> applyObserverSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread());
    }

    public static CompletableTransformer applyCompletableSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread());
    }

    public static <T> ObservableTransformer<T, T> applyErrorTransformer() {
        return observable -> observable.onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends T>>) throwable -> {
            if (!NetworkUtils.isConnected(AppController.getInstance())) {
                return Observable.error(new RuntimeException(AppConstants.EXCEPTION_NO_NETWORK_CONNECTION));
            } else if (throwable instanceof UnknownHostException) {
                return Observable.error(new RuntimeException(AppConstants.EXCEPTION_REQUEST_TIMEOUT));
            } else if (throwable instanceof SocketTimeoutException) { // Slow Internet Connection
                return Observable.error(new RuntimeException(AppConstants.EXCEPTION_REQUEST_TIMEOUT));
            } else if (throwable instanceof ApiHttpException) {
                Response response = ((ApiHttpException) throwable).response();
                if (AppException.isAppException(response)) {
                    try {
                        return Observable.error(AppException.create(response));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return Observable.error(throwable);
        });
    }

    public static <T> ObservableTransformer<T, T> applyOnErrorCrasher() {
        return observable -> observable.doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                final Throwable checkpoint = new Throwable();
                StackTraceElement[] stackTrace = checkpoint.getStackTrace();
                StackTraceElement element = stackTrace[1]; // First element after `crashOnError()`
                String msg = String.format("onError() crash from subscribe() in %s.%s(%s:%s)",
                        element.getClassName(),
                        element.getMethodName(),
                        element.getFileName(),
                        element.getLineNumber());

                throw new OnErrorNotImplementedException(msg, throwable);
            }
        });

    }
}
