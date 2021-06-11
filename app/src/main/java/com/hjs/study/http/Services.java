package com.hjs.study.http;

import com.google.gson.JsonObject;
import com.hjs.study.model.ApiBase;
import com.hjs.study.tr.TR_ISSUE03;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Services {

    String API_BASE = "https:/rassiappdev.thinkpool.com:56620/rassi_and/";

    @Headers({"Content-type: application/json"})
    @POST("TR_ISSUE03")
    Observable<ApiBase<TR_ISSUE03>> getObIssue03(@Body JsonObject jsonObject);

}