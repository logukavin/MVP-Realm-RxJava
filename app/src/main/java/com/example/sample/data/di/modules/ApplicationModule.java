package com.example.sample.data.di.modules;

import android.app.Application;

import com.example.sample.data.navigator.Navigator;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

    @Provides
    Navigator providesNavigator() {
        return new Navigator();
    }



}
