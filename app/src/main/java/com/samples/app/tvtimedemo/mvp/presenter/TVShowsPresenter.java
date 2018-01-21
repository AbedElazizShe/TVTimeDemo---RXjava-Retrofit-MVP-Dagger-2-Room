package com.samples.app.tvtimedemo.mvp.presenter;

import com.samples.app.tvtimedemo.api.ApiService;
import com.samples.app.tvtimedemo.base.BasePresenter;
import com.samples.app.tvtimedemo.mapper.TVShowsMapper;
import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.mvp.model.TVShowsResponse;
import com.samples.app.tvtimedemo.mvp.view.MainView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by $Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class TVShowsPresenter extends BasePresenter<MainView> implements Observer<TVShowsResponse> {

    @Inject
    protected ApiService mApiService;

    @Inject
    protected TVShowsMapper mTVShowsMapper;

    private Observable<TVShowsResponse> mTVShowsObservable;

    @Inject
    public TVShowsPresenter() {
    }

    public void getTVShows(String type, Map<String, String> queryMap) {
        getView().onShowDialog();
        mTVShowsObservable = mApiService.fetchTVShows(type, queryMap);
        subscribe(mTVShowsObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowMessage("Data loading completed");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowMessage("Error loading data");

        // retry 3 times with a 5s, 10s, then 15s delay
        mTVShowsObservable.retryWhen(attempt -> attempt
                .zipWith(Observable.range(1,3), (n,i) -> i)
                .flatMap(i -> Observable.timer(5 * i, TimeUnit.SECONDS))
        );

        subscribe(mTVShowsObservable, this);
    }

    @Override
    public void onNext(TVShowsResponse tvShowsResponse) {
        List<Result> tvShows = mTVShowsMapper.mapTVShows(tvShowsResponse);

        getView().onClearItems();
        getView().onDataLoaded(tvShows);
    }

    private void addTVShow(Result result){

    }
}
