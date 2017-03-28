package com.codechris.bucketdrops;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.content.ContentValues.TAG;

/**
 * Created by Christopher on 3/25/2017.
 */

public class appBucketDrops extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " +"AppBucket");
        Realm.init(this);
        Log.d(TAG, "post realm init");
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
