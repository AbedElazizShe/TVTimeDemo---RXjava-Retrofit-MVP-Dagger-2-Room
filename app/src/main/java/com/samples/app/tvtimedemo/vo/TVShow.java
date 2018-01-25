package com.samples.app.tvtimedemo.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */


public class TVShow implements Parcelable {

    private List<TVShow> tvShows;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    private Long id;
    private String originalName;
    private String firstAirDate;
    private String backdropPath;
    private String overview;

    public TVShow(Long id, String originalName, String firstAirDate, String backdropPath, String overview) {
        this.id = id;
        this.originalName = originalName;
        this.firstAirDate = firstAirDate;
        this.backdropPath = backdropPath;
        this.overview = overview;

    }

    public Long getId() {
        return id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public TVShow(Parcel in) {
        tvShows = new ArrayList<>();
        in.readTypedList(tvShows, TVShow.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(tvShows);
    }
}
