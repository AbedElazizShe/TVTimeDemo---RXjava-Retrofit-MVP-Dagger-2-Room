package com.samples.app.tvtimedemo.db;

import android.arch.lifecycle.LiveData;

import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;

import java.util.List;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */
public interface LocalDataRepository {

    LiveData<List<TVShowsEntity>> getTVShows();

    void insertTVShows(TVShowsEntity tvShowsEntity);

    LiveData<TVShowsEntity> getTVShowById(long id);
}
