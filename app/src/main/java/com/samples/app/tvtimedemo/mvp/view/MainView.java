package com.samples.app.tvtimedemo.mvp.view;

import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.mvp.model.TVShowsResponse;

import java.util.List;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public interface MainView extends BaseView {

    void onDataLoaded(List<Result> tvShows);

    void onShowDialog();

    void onHideDialog();

    void onShowMessage(String message);

    void onClearItems();
}
