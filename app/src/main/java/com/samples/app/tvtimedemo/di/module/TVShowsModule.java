package com.samples.app.tvtimedemo.di.module;

import com.samples.app.tvtimedemo.api.ApiService;
import com.samples.app.tvtimedemo.di.scope.PerActivity;
import com.samples.app.tvtimedemo.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Module
public class TVShowsModule {

    private MainView mView;

    public TVShowsModule(MainView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }
}
