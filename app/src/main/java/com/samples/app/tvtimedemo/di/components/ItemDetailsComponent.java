package com.samples.app.tvtimedemo.di.components;

import com.samples.app.tvtimedemo.di.module.RoomModule;
import com.samples.app.tvtimedemo.di.scope.PerActivity;
import com.samples.app.tvtimedemo.ui.activity.ItemDetailsActivity;

import dagger.Component;

/**
 * Created by $Abed Elaziz Shehadeh on 07, March, 2018
 * elaziz.shehadeh@gmail.com
 */

@PerActivity
@Component(modules = {RoomModule.class}, dependencies = ApplicationComponent.class)
public interface ItemDetailsComponent {

    void inject(ItemDetailsActivity activity);
}
