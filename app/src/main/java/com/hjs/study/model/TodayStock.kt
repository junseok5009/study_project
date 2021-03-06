package com.hjs.study.model

import com.google.gson.annotations.SerializedName

data class TodayStock(
    @SerializedName("title") var title: String,
    @SerializedName("stockCode")val stockCode: String,
    @SerializedName("stockName")var stockName: String,
    @SerializedName("tradeDate")val tradeDate: String,
    @SerializedName("tradeTime")val tradeTime: String,
    @SerializedName("tradeFlag")val tradeFlag: String,
    @SerializedName("profitRate")val profitRate: String
){
    constructor(
        title: String,
        stockCode: String,): this(title, stockCode, "", "", "", "", "")

}

