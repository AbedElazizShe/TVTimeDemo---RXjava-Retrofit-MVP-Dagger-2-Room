package com.samples.app.tvtimedemo.di.components;

import com.samples.app.tvtimedemo.di.module.TVShowsModule;
import com.samples.app.tvtimedemo.di.scope.PerActivity;
import com.samples.app.tvtimedemo.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@PerActivity
@Component(modules = {TVShowsModule.class}, dependencies = ApplicationComponent.class)
public interface TVShowsComponent {

    void inject(MainActivity mainActivity);
}
