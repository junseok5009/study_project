package com.hjs.study.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjs.study.model.User

class MainViewModel: ViewModel() {

    var liveData: MutableLiveData<ArrayList<User>> = MutableLiveData()

    init {
        val al_user = ArrayList<User>().apply {
            add(User("김광현","빛광현"))
            add(User("류현진","뚱땡이"))
            add(User("양현종","양치기소년"))
       }
        liveData.postValue(al_user)
    }



}