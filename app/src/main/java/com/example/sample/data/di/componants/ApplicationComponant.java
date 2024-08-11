package com.example.sample.data.di.componants;

import android.app.Application;


import com.example.sample.data.di.modules.ApplicationModule;
import com.example.sample.data.navigator.Navigator;

import dagger.Component;


@Component(modules = {ApplicationModule.class})
public interface ApplicationComponant {

    Application getApplication();

    Navigator getNavigator();



}
