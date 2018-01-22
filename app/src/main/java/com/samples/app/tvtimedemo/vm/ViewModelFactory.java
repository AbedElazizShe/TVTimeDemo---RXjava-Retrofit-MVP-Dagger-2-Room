package com.samples.app.tvtimedemo.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.samples.app.tvtimedemo.db.LocalDataRepository;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class ViewModelFactory implements ViewModelProvider.Factory{

    private final LocalDataRepository mLocalDataRepository;

    public ViewModelFactory(LocalDataRepository mLocalDataRepository) {
        this.mLocalDataRepository = mLocalDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(mLocalDataRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
