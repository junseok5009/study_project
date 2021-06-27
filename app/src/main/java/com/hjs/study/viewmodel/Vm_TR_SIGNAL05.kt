package com.hjs.study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjs.study.tr.TR_SIGNAL05
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class Vm_TR_SIGNAL05: ViewModel() {

    var _liveData : MutableLiveData<TR_SIGNAL05> = MutableLiveData()
    private val liveData : LiveData<TR_SIGNAL05> get() = _liveData
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun clearVmCompositeDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}