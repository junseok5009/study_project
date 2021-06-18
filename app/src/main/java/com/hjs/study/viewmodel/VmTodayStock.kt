package com.hjs.study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjs.study.model.TodayStock

class VmTodayStock: ViewModel() {

    val _liveData : MutableLiveData<ArrayList<TodayStock>> = MutableLiveData()
    //val liveData: LiveData<ArrayList<TodayStock>> get() = _liveData


    init {

    }


    override fun onCleared() {
        super.onCleared()

    }
}