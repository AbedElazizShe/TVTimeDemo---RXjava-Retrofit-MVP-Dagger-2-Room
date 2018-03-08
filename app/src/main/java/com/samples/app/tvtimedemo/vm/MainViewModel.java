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
    private final MediatorLiveData<TVShowsEntity> mObservableTVShow;

    private LocalDataRepository localDataRepository;


    public MainViewModel(LocalDataRepository localDataRepository) {
        this.localDataRepository = localDataRepository;
        this.mObservableTVShows = new MediatorLiveData<>();
        this.mObservableTVShow = new MediatorLiveData<>();


        mObservableTVShows.setValue(null);
        mObservableTVShow.setValue(null);

        LiveData<List<TVShowsEntity>> tvShows = localDataRepository.getTVShows();
        mObservableTVShows.addSource(tvShows, mObservableTVShows::setValue);



    }

    public LiveData<List<TVShowsEntity>> getTVShows() {
        return mObservableTVShows;
    }

    public LiveData<TVShowsEntity> getTVShowById(long id) {

        LiveData<TVShowsEntity> tvShow = localDataRepository.getTVShowById(id);
        mObservableTVShow.addSource(tvShow, mObservableTVShow::setValue);


        return mObservableTVShow;
    }
}
