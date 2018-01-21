package com.samples.app.tvtimedemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.samples.app.tvtimedemo.R;
import com.samples.app.tvtimedemo.application.App;
import com.samples.app.tvtimedemo.di.components.ApplicationComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mUnbinder = ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
    }

    protected void resolveDaggerDependency() {
    }

    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((App) getApplication()).getApplicationComponent();
    }

    protected void getToolbar(final int resId) {
        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar_actionbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);

                mToolbar.setTitle(getResources().getString(resId));

            }
        }

    }
}
