package com.samples.app.tvtimedemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.samples.app.tvtimedemo.R;
import com.samples.app.tvtimedemo.base.BaseActivity;
import com.samples.app.tvtimedemo.di.components.DaggerTVShowsComponent;
import com.samples.app.tvtimedemo.di.module.TVShowsModule;
import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.mvp.presenter.TVShowsPresenter;
import com.samples.app.tvtimedemo.mvp.view.MainView;
import com.samples.app.tvtimedemo.ui.adapter.RecyclerViewAdapter;
import com.samples.app.tvtimedemo.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.samples.app.tvtimedemo.util.Constants.TEMP_TOKEN;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.tvShowsList)
    protected RecyclerView mRecyclerView;

    @Inject
    protected TVShowsPresenter mPresenter;

    private List<Result> data = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        getToolbar(R.string.home);
        setUpTVShowsList();
        loadTVShows();
    }

    private void loadTVShows() {

        if (NetworkUtil.isConnected(getApplicationContext())) {
            String type = "tv";
            Map<String, String> map = new HashMap<>();
            map.put("sort_by", "popularity.desc");
            map.put("page", "1");
            map.put("api_key", TEMP_TOKEN);
            mPresenter.getTVShows(type, map);
        } else {
            /*
             * Todo: Load data from offline database - ROOM will be used for that purpose
             */
        }
    }

    private void setUpTVShowsList() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerViewAdapter(data, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDataLoaded(List<Result> tvShows) {
        data.addAll(tvShows);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowDialog() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onShowMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        data.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        super.resolveDaggerDependency();

        DaggerTVShowsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .tVShowsModule(new TVShowsModule(this))
                .build()
                .inject(this);
    }
}
