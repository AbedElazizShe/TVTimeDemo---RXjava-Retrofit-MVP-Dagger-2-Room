package com.samples.app.tvtimedemo.mapper;

import android.support.annotation.NonNull;

import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.mvp.model.TVShowsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by $Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class TVShowsMapper {

    @Inject
    public TVShowsMapper() {
    }

    public List<Result> mapTVShows(@NonNull TVShowsResponse response) {
        List<Result> list = new ArrayList<>();

        list.addAll(response.getResults());

        return list;

    }
}
