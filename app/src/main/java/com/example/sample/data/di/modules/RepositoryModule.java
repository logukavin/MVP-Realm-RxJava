package com.example.sample.data.di.modules;


import com.example.sample.BuildConfig;
import com.example.sample.data.api.ApiConstants;
import com.example.sample.data.api.ApiInterface;
import com.example.sample.data.repo.AppRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RepositoryModule {

    @Provides
    Gson providesGson() {
        return new GsonBuilder().setLenient().serializeNulls().create();
    }

    @Provides
    ApiInterface providesApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(providesHttpLoggingInterceptor())
                .build();
    }

    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(providesGson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BuildConfig.DOMAIN_URL)
                .client(providesOkHttpClient())
                .build();
    }

    @Provides
    AppRepo providesRepo() {
        return new AppRepo(providesApiInterface(providesRetrofit()));
    }



}
