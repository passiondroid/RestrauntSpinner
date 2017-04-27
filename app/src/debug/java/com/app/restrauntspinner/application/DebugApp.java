package com.app.restrauntspinner.application;

import com.facebook.stetho.Stetho;

/**
 * Created by arifkhan on 17/12/16.
 */

public class DebugApp extends RSApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

    }
}
