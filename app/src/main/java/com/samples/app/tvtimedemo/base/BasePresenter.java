package com.samples.app.tvtimedemo.base;

import com.samples.app.tvtimedemo.mvp.view.BaseView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by $Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class BasePresenter<V extends BaseView> {

    @Inject protected V mView;

    protected V getView(){
        return mView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
