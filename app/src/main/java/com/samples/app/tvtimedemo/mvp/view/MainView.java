package com.samples.app.tvtimedemo.mvp.view;

import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.mvp.model.TVShowsResponse;
import com.samples.app.tvtimedemo.vo.TVShow;

import java.util.List;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public interface MainView extends BaseView {

    void onDataLoaded(List<TVShow> tvShows, long page, long totalPages);

    void onShowDialog();

    void onHideDialog();

    void onShowMessage(String message);

    void onClearItems();
}
