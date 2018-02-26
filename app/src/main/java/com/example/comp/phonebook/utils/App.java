package com.example.comp.phonebook.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by COMP on 26.2.2018..
 */

public class App extends Application {

    private static App appInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private static void setInstance(App app) {
        appInstance = app;
    }

    private static App getInstance() {
        return appInstance;
    }
}
