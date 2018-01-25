package com.samples.app.tvtimedemo.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.samples.app.tvtimedemo.db.LocalDataRepository;
import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;

import java.util.List;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */
public class MainViewModel extends ViewModel {

    private final MediatorLiveData<List<TVShowsEntity>> mObservableTVShows;


    public MainViewModel(LocalDataRepository localDataRepository) {
        this.mObservableTVShows = new MediatorLiveData<>();
        mObservableTVShows.setValue(null);

        LiveData<List<TVShowsEntity>> tvShows = localDataRepository.getTVShows();
        mObservableTVShows.addSource(tvShows, mObservableTVShows::setValue);

    }

    public LiveData<List<TVShowsEntity>> getTVShows() {
        return mObservableTVShows;
    }
}
