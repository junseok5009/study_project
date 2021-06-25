package com.hjs.study.http;

import com.google.gson.JsonObject;
import com.hjs.study.model.ApiBase;
import com.hjs.study.model.TodayStock;
import com.hjs.study.tr.TR_ISSUE03;
import com.hjs.study.tr.TR_SIGNAL05;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Services {

    String API_BASE = "https:/rassiappdev.thinkpool.com:56620/rassi_and/";
    String WEB_BASE = "https://olla.thinkpool.com/user/api/ajax/";

    @Headers({"Content-type:application/json"})
    @POST("TR_ISSUE03")
    Observable<ApiBase<TR_ISSUE03>> getObIssue03(@Body JsonObject jsonObject);

    @Headers({"Content-type:application/json"})
    @POST("TR_TODAY01")
    Observable<ApiBase<ArrayList<TodayStock>>> getObToday01(@Body JsonObject jsonObject);

    @POST("TR_SIGNAL05")
    Observable<ApiBase<TR_SIGNAL05>> getSignal05(@Body JsonObject jsonObject);

    @Headers({"Content-type:application/x-www-form-urlencoded"})
    @POST("userInfoProcAjax.do")
    Call<String> getUserInfoProcAjax(@Body String param);



}