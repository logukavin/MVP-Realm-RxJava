package com.example.sample.app;

import android.app.Application;

import com.example.sample.data.di.componants.AppPreferenceComponant;
import com.example.sample.data.di.componants.ApplicationComponant;
import com.example.sample.data.di.componants.DaggerAppPreferenceComponant;
import com.example.sample.data.di.componants.DaggerApplicationComponant;
import com.example.sample.data.di.componants.DaggerRepositoryComponant;
import com.example.sample.data.di.componants.RepositoryComponant;
import com.example.sample.data.di.modules.AppPreferenceModule;
import com.example.sample.data.di.modules.ApplicationModule;
import com.example.sample.data.di.modules.RepositoryModule;
import com.example.sample.data.navigator.Navigator;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.Realm;
import io.realm.RealmConfiguration;
//import io.realm.Realm;
//import io.realm.RealmConfiguration;

public class AppController extends Application {

    private static AppController appController;

    private ApplicationComponant applicationComponant;
    private AppPreferenceComponant appPreferenceComponant;
    private RepositoryComponant repositoryComponant;

    private Gson gson;


    public static AppController getInstance() {
        return appController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        // Initialize Realm
        Realm.init(this);
        // Configure Realm (optional)
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1) // Must be bumped when the schema changes
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        applicationComponant = DaggerApplicationComponant.builder().applicationModule(new ApplicationModule(this)).build();
        appPreferenceComponant = DaggerAppPreferenceComponant.builder().appPreferenceModule(new AppPreferenceModule(this)).build();
        repositoryComponant = DaggerRepositoryComponant.builder().repositoryModule(new RepositoryModule()).build();

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

    }


    public RepositoryComponant getRepositoryComponant() {
        return repositoryComponant;
    }

    public Navigator getNavigator() {
        return applicationComponant.getNavigator();
    }

    public AppPreference getAppPreference() {
        return appPreferenceComponant.getAppPreference();
    }


}
