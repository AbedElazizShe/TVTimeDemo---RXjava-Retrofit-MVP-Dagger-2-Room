package com.samples.app.tvtimedemo.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.samples.app.tvtimedemo.R;
import com.samples.app.tvtimedemo.base.BaseActivity;
import com.samples.app.tvtimedemo.db.entity.TVShowsEntity;
import com.samples.app.tvtimedemo.di.components.DaggerItemDetailsComponent;
import com.samples.app.tvtimedemo.di.module.RoomModule;
import com.samples.app.tvtimedemo.util.ImageLoader;
import com.samples.app.tvtimedemo.vm.MainViewModel;
import com.samples.app.tvtimedemo.vm.ViewModelFactory;
import com.samples.app.tvtimedemo.vo.TVShow;

import javax.inject.Inject;

import butterknife.BindView;

public class ItemDetailsActivity extends BaseActivity {

    @BindView(R.id.imageView)
    protected ImageView imageView;

    @BindView(R.id.titleTextView)
    protected TextView titleTextView;

    @BindView(R.id.previewTextView)
    protected TextView previewTextView;

    private MainViewModel mMainViewModel;
    private long id;

    @Inject
    protected ViewModelFactory mViewModelFactory;

    private ImageLoader mImageLoader;

    public static void start(Activity activity, long id){
        Intent intent = new Intent(activity, ItemDetailsActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_item_details;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        if(intent.getExtras() != null){
            id = intent.getLongExtra("id", 0L);
        }

        getToolbar(R.string.overView);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mImageLoader = new ImageLoader(this);
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);


        loadOfflineTVShows(mMainViewModel);

    }

    private void loadOfflineTVShows(MainViewModel mainViewModel) {

        mainViewModel.getTVShowById(id).observe(this, tvShowEntity -> {

            if(tvShowEntity != null) {
                mImageLoader.loadImage(tvShowEntity.getImagePath(), imageView);
                titleTextView.setText(tvShowEntity.getName());
                previewTextView.setText(tvShowEntity.getOverview());
            }


        });
    }

    @Override
    protected void resolveDaggerDependency() {
        super.resolveDaggerDependency();

        DaggerItemDetailsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
