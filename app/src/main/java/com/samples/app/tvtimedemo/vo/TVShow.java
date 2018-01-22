package com.samples.app.tvtimedemo.vo;

/**
 * Created by Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */


public class TVShow {

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
}
