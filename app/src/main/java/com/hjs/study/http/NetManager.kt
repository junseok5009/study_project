package com.hjs.study.http

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.hjs.study.model.ApiBase
import com.hjs.study.model.TodayStock
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import io.reactivex.rxjava3.core.Observable
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.scalars.ScalarsConverterFactory

object NetManager {

    private var client: OkHttpClient

    init {
        client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .cache(null)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    }

    val rxJavaRetrofit: Retrofit
        get(){
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(Services.API_BASE)
                .client(client)
                .build()
        }

    fun getRxJavaRtrofitWeb(): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            //.addConverterFactory(GsonConverterFactory.create(gson))
            //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(Services.WEB_BASE)
            .client(client)
            .build()
    }



}