package com.samples.app.tvtimedemo.di.components;

import com.samples.app.tvtimedemo.di.module.ApplicationModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Executor exposeExecutor();
}
