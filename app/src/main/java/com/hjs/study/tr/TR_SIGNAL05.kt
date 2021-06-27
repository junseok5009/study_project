package com.hjs.study.tr

import com.google.gson.annotations.SerializedName
import com.hjs.study.model.HonorStock
import com.hjs.study.model.Signal

data class TR_SIGNAL05(
    @SerializedName("updateDttm") val updateDttm: String,
    @SerializedName("buyCount") val buyCount: String,
    @SerializedName("sellCount") val sellCount: String,
    @SerializedName("list_Signal") val list_Signal: ArrayList<Signal>,
    @SerializedName("list_HonorStock") val list_HonorStock: ArrayList<HonorStock>
){
    //constructor()
}
