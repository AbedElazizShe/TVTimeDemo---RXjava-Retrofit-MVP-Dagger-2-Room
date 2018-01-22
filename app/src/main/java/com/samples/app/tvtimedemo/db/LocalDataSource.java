package com.samples.app.tvtimedemo.db;

import android.arch.lifecycle.LiveData;

import com.samples.app.tvtimedemo.db.dao.TVShowsDao;
import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class LocalDataSource implements LocalDataRepository {

    private TVShowsDao mTVShowsDao;
    private Executor mExecutor;

    public LocalDataSource(TVShowsDao TVShowsDao, Executor executor) {
        mTVShowsDao = TVShowsDao;
        mExecutor = executor;
    }

    @Override
    public LiveData<List<TVShowsEntity>> getTVShows() {
        return mTVShowsDao.loadSavedTVShows();
    }

    @Override
    public void insertTVShows(TVShowsEntity tvShowsEntity) {
        mExecutor.execute(() -> mTVShowsDao.insertTVShows(tvShowsEntity));
    }
}
