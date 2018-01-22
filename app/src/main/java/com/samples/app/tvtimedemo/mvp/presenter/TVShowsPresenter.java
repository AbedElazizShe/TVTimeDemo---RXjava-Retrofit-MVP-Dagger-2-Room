package com.samples.app.tvtimedemo.mvp.presenter;


import android.support.annotation.NonNull;

import com.samples.app.tvtimedemo.api.ApiService;
import com.samples.app.tvtimedemo.base.BasePresenter;
import com.samples.app.tvtimedemo.db.LocalDataRepository;
import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;
import com.samples.app.tvtimedemo.mapper.TVShowsMapper;
import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.mvp.model.TVShowsResponse;
import com.samples.app.tvtimedemo.mvp.view.MainView;
import com.samples.app.tvtimedemo.vo.TVShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

import static com.samples.app.tvtimedemo.util.Constants.IMAGE_BASE_URL;

/**
 * Created by Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class TVShowsPresenter extends BasePresenter<MainView> implements Observer<TVShowsResponse> {

    @Inject
    protected ApiService mApiService;

    @Inject
    protected TVShowsMapper mTVShowsMapper;

    @Inject
    protected LocalDataRepository mLocalDataRepository;

    private Observable<TVShowsResponse> mTVShowsObservable;

    @NonNull
    private Subscription mSubscription;

    @Inject
    public TVShowsPresenter() {
    }

    public void unSubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public void getTVShows(String type, Map<String, String> queryMap) {
        getView().onShowDialog();
        mTVShowsObservable = mApiService.fetchTVShows(type, queryMap);
        mSubscription = subscribe(mTVShowsObservable, this);
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
                .zipWith(Observable.range(1, 3), (n, i) -> i)
                .flatMap(i -> Observable.timer(5 * i, TimeUnit.SECONDS))
        );

        mSubscription = subscribe(mTVShowsObservable, this);
    }

    @Override
    public void onNext(TVShowsResponse tvShowsResponse) {
        List<Result> tvShows = mTVShowsMapper.mapTVShows(tvShowsResponse);

        // Add to database
        for (Result result : tvShows) {
            addTVShow(result);
        }

        List<TVShow> data = new ArrayList<>();
        for(Result result: tvShows){
            data.add(new TVShow(
                    result.getId(),
                    result.getOriginalName(),
                    result.getFirstAirDate(),
                    IMAGE_BASE_URL + result.getBackdropPath(),
                    result.getOverview()
            ));
        }


        getView().onDataLoaded(data, tvShowsResponse.getPage(), tvShowsResponse.getTotalPages());
    }

    private void addTVShow(Result result) {
        TVShowsEntity tvShowsEntity = new TVShowsEntity();
        tvShowsEntity.showId = result.getId();
        tvShowsEntity.name = result.getOriginalName();
        tvShowsEntity.firstAirDate = result.getFirstAirDate();
        tvShowsEntity.imagePath = (IMAGE_BASE_URL + result.getBackdropPath());
        tvShowsEntity.overview = result.getOverview();

        mLocalDataRepository.insertTVShows(tvShowsEntity);

    }
}
