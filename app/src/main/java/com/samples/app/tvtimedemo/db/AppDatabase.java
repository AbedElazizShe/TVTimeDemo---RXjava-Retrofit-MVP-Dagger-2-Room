package com.samples.app.tvtimedemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.samples.app.tvtimedemo.db.dao.TVShowsDao;
import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Database(entities = {TVShowsEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TVShowsDao tvShowsDao();

}
