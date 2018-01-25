package com.samples.app.tvtimedemo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Dao
public interface TVShowsDao {

    @Insert(onConflict = REPLACE)
    void insertTVShows(TVShowsEntity tvShowsEntity);

    @Query("select * from tv_shows order by popularity desc limit 50")
    LiveData<List<TVShowsEntity>> loadSavedTVShows();



}
