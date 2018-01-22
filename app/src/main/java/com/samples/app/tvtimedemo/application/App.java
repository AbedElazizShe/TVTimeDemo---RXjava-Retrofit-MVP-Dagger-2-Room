package com.samples.app.tvtimedemo.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.samples.app.tvtimedemo.BuildConfig;
import com.samples.app.tvtimedemo.di.components.ApplicationComponent;
import com.samples.app.tvtimedemo.di.components.DaggerApplicationComponent;
import com.samples.app.tvtimedemo.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

import static com.samples.app.tvtimedemo.util.Constants.BASE_URL;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class App extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeApplicationComponent();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            /*
            Access chrome://inspect/#devices to debug the application such as database inspection
            and network inspection
             */
            Stetho.initializeWithDefaults(this);
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
