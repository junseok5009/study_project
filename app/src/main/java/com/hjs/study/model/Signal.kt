package com.hjs.study.model

import com.google.gson.annotations.SerializedName

data class Signal(
    @SerializedName("tradeFlag") val tradeFlag: String,
    @SerializedName("tradeTime") val tradeTime: String,
    @SerializedName("tradeCount") val tradeCount: String
)
