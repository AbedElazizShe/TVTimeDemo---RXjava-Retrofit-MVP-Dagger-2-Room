package com.samples.app.tvtimedemo.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Entity(tableName = "tv_shows", indices = {@Index(value =
        {"show_id"}, unique = true)})
public class TVShowsEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "show_id")
    public Long showId;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "first_air_date")
    public String firstAirDate;

    @ColumnInfo(name = "image_path")
    public String imagePath;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "popularity")
    public Double popularity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long id) {
        this.showId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
}
