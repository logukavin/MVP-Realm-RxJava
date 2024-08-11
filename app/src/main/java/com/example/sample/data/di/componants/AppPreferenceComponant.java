package com.example.sample.data.di.componants;


import com.example.sample.app.AppPreference;
import com.example.sample.data.di.modules.AppPreferenceModule;

import dagger.Component;

@Component(modules = {AppPreferenceModule.class})
public interface AppPreferenceComponant {

    AppPreference getAppPreference();

}
