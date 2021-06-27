package com.hjs.study.model

import com.google.gson.annotations.SerializedName

data class HonorStock(
    @SerializedName("honorDi") val honorDi: String,
    @SerializedName("honorName") val honorName: String,
    @SerializedName("tradeFlag") val tradeFlag: String,
    @SerializedName("tradeTime") val tradeTime: String,
    @SerializedName("stockCode") val stockCode: String,
    @SerializedName("stockName") val stockName: String
)
