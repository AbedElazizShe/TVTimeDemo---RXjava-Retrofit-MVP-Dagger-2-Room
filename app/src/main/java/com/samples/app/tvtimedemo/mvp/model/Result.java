
package com.samples.app.tvtimedemo.mvp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Result {

    @JsonProperty("backdrop_path")
    private String mBackdropPath;
    @JsonProperty("first_air_date")
    private String mFirstAirDate;
    @JsonProperty("genre_ids")
    private List<Long> mGenreIds;
    @JsonProperty("id")
    private Long mId;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("origin_country")
    private List<String> mOriginCountry;
    @JsonProperty("original_language")
    private String mOriginalLanguage;
    @JsonProperty("original_name")
    private String mOriginalName;
    @JsonProperty("overview")
    private String mOverview;
    @JsonProperty("popularity")
    private Double mPopularity;
    @JsonProperty("poster_path")
    private String mPosterPath;
    @JsonProperty("vote_average")
    private Double mVoteAverage;
    @JsonProperty("vote_count")
    private Long mVoteCount;

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return mFirstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        mFirstAirDate = firstAirDate;
    }

    public List<Long> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        mGenreIds = genreIds;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<String> getOriginCountry() {
        return mOriginCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        mOriginCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return mOriginalName;
    }

    public void setOriginalName(String originalName) {
        mOriginalName = originalName;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

}
