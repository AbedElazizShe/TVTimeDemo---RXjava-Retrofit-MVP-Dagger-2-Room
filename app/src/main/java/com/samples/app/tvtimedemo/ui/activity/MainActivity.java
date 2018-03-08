package com.samples.app.tvtimedemo.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.samples.app.tvtimedemo.R;
import com.samples.app.tvtimedemo.base.BaseActivity;
import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;
import com.samples.app.tvtimedemo.di.components.DaggerTVShowsComponent;
import com.samples.app.tvtimedemo.di.module.RoomModule;
import com.samples.app.tvtimedemo.di.module.TVShowsModule;
import com.samples.app.tvtimedemo.mvp.presenter.TVShowsPresenter;
import com.samples.app.tvtimedemo.mvp.view.MainView;
import com.samples.app.tvtimedemo.ui.adapter.RecyclerViewAdapter;
import com.samples.app.tvtimedemo.util.NetworkUtil;
import com.samples.app.tvtimedemo.util.RecyclerViewUtils;
import com.samples.app.tvtimedemo.vm.MainViewModel;
import com.samples.app.tvtimedemo.vm.ViewModelFactory;
import com.samples.app.tvtimedemo.vo.TVShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

import static com.samples.app.tvtimedemo.util.Constants.SHOWS_CURRENT_PAGE;
import static com.samples.app.tvtimedemo.util.Constants.SHOWS_TOTAL_PAGES;
import static com.samples.app.tvtimedemo.util.Constants.TEMP_TOKEN;
import static com.samples.app.tvtimedemo.util.Constants.TV_SHOWS_DATA;

public class MainActivity extends BaseActivity implements MainView, RecyclerViewAdapter.OnItemClicked {

    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.tvShowsList)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout mSwipeContainer;

    @Inject
    protected TVShowsPresenter mPresenter;

    private ArrayList<TVShow> data = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;

    private long mPage;
    private long mTotalPages;

    @Inject
    protected ViewModelFactory mViewModelFactory;

    private MainViewModel mMainViewModel;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);

        getToolbar(R.string.home);
        setUpTVShowsList();

        if(savedInstanceState != null){
            data.addAll(savedInstanceState.getParcelableArrayList(TV_SHOWS_DATA));
            mAdapter.notifyDataSetChanged();
            mPage = savedInstanceState.getLong(SHOWS_CURRENT_PAGE);
            mTotalPages = savedInstanceState.getLong(SHOWS_TOTAL_PAGES);
            onHideDialog();
        }else {
            loadTVShows("1");
        }

        if (NetworkUtil.isConnected(getApplicationContext())) {
            loadMoreData();
        }

        refreshData();

    }

    private void loadTVShows(String page) {

        if (NetworkUtil.isConnected(getApplicationContext())) {
            Map<String, String> map = new HashMap<>();
            map.put("sort_by", "popularity.desc");
            map.put("page", page);
            map.put("api_key", TEMP_TOKEN);
            mPresenter.getTVShows("tv", map);
        } else {
            loadOfflineTVShows(mMainViewModel);
        }
    }

    private void loadOfflineTVShows(MainViewModel mainViewModel) {
        onShowDialog();

        mainViewModel.getTVShows().observe(this, tvShowsEntities -> {
            if (tvShowsEntities != null) {
                data.clear();
                for (TVShowsEntity tvShows : tvShowsEntities) {
                    data.add(new TVShow(
                            tvShows.getShowId(),
                            tvShows.getName(),
                            tvShows.getFirstAirDate(),
                            tvShows.getImagePath(),
                            tvShows.getOverview()));
                }

                mAdapter.notifyDataSetChanged();
                onHideDialog();
            }
        });
    }

    private void setUpTVShowsList() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerViewAdapter(data, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadMoreData() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (RecyclerViewUtils.isLastItemDisplaying(recyclerView))
                    if (mPage <= mTotalPages) {
                        loadTVShows(String.valueOf(mPage));
                    }
            }
        });
    }

    private void refreshData() {
        mSwipeContainer.setOnRefreshListener(() -> loadTVShows("1"));
    }

    @Override
    public void onDataLoaded(List<TVShow> tvShows, long page, long totalPages) {

        if (page == 1) {
            onClearItems();
        }

        mPage = page + 1;
        mTotalPages = totalPages;

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
        if (mSwipeContainer.isRefreshing()) {
            mSwipeContainer.setRefreshing(false);
        }
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
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void onItemClickedListener(int position) {
        ItemDetailsActivity.start(this, data.get(position).getId());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TV_SHOWS_DATA, data);
        outState.putLong(SHOWS_CURRENT_PAGE, mPage);
        outState.putLong(SHOWS_TOTAL_PAGES, mTotalPages);
    }
}
