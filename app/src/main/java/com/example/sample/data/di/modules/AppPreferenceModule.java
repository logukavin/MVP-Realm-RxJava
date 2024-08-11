package com.example.sample.data.di.modules;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.sample.app.AppPreference;

import dagger.Module;
import dagger.Provides;


@Module
public class AppPreferenceModule {

    private Context context;

    public AppPreferenceModule(Context context) {
        this.context = context;
    }

    @Provides
    @NonNull
    AppPreference providesAppPreference() {
        return new AppPreference(context);
    }

}
