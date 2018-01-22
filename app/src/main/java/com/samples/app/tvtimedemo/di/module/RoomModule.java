package com.samples.app.tvtimedemo.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.samples.app.tvtimedemo.db.AppDatabase;
import com.samples.app.tvtimedemo.db.LocalDataRepository;
import com.samples.app.tvtimedemo.db.LocalDataSource;
import com.samples.app.tvtimedemo.db.dao.TVShowsDao;
import com.samples.app.tvtimedemo.di.scope.PerActivity;
import com.samples.app.tvtimedemo.vm.ViewModelFactory;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Abed Elaziz Shehadeh on 22, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Module
public class RoomModule {

    private AppDatabase mAppDatabase;

    public RoomModule(Application application) {
        this.mAppDatabase = Room.databaseBuilder(application, AppDatabase.class, "demo_db")
                .build();
    }

    @PerActivity
    @Provides
    AppDatabase provideAppDatabase(){
        return mAppDatabase;
    }

    @PerActivity
    @Provides
    TVShowsDao provideCarDao(AppDatabase appDatabase){
        return appDatabase.tvShowsDao();
    }

    @PerActivity
    @Provides
    LocalDataRepository provideLocalDataRepository(TVShowsDao tvShowsDao, Executor executor){

        return new LocalDataSource(tvShowsDao, executor);
    }

    @PerActivity
    @Provides
    ViewModelFactory provideViewModelFactory(LocalDataRepository localDataRepository){
        return new ViewModelFactory(localDataRepository);
    }
}
