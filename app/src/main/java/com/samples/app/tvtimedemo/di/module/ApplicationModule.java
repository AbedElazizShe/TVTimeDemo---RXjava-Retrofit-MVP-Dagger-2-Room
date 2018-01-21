package com.samples.app.tvtimedemo.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Module
public class ApplicationModule {

    private Context mContext;
    private String mBaseUrl;

    public ApplicationModule(Context context, String baseUrl) {
        mContext = context;
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    JacksonConverterFactory provideJaksonConverterFactory(ObjectMapper objectMapper) {

        return JacksonConverterFactory.create(objectMapper);
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    Dispatcher provideDispatcher(){
        return new Dispatcher();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor, Dispatcher dispatcher){
        return new OkHttpClient
                .Builder()
                .addInterceptor(chain -> {
                    Request.Builder ongoing = chain.request().newBuilder();
                    return chain.proceed(ongoing.build());
                })
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .build();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    ObjectMapper provideObjectMapper(){
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, JacksonConverterFactory converterFactory,
                             RxJavaCallAdapterFactory adapterFactory) {

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    Executor provideExecutor(){
        return Executors.newFixedThreadPool(2);
    }

}
