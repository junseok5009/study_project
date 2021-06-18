package com.hjs.study.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.hjs.study.activity.sta_tag
import com.hjs.study.adapter.TodayStockRcvAdapter
import com.hjs.study.databinding.FmMainHomeHomeBinding
import com.hjs.study.http.NetManager
import com.hjs.study.http.Services
import com.hjs.study.model.ApiBase
import com.hjs.study.model.TodayStock
import com.hjs.study.viewmodel.VmTodayStock
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer

class FmMainHome_Home :Fragment(){

    private val mld_al_todayStock = MutableLiveData<ArrayList<TodayStock>>()
    private lateinit var vmTodayStock: VmTodayStock


    init {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmTodayStock = ViewModelProvider(this).get(com.hjs.study.viewmodel.VmTodayStock::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding : FmMainHomeHomeBinding = FmMainHomeHomeBinding.inflate(LayoutInflater.from(context), container, false)
        binding.fmMHHTodayStockRcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val dataObserver: Observer<ArrayList<TodayStock>> =Observer{ livedata ->
            mld_al_todayStock.value = livedata
            binding.fmMHHTodayStockRcv.adapter = TodayStockRcvAdapter(mld_al_todayStock)
        }

        vmTodayStock._liveData.observe(viewLifecycleOwner, dataObserver)


        request_TODAY01()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun request_TODAY01(){
        val jsonObj = JsonObject().apply {
            addProperty("userId", "0000179986@sa")
        }
        val services: Services = NetManager.getRxJavaRetrofit().create(Services::class.java)
        val observable = services.getObToday01(jsonObj)
       CompositeDisposable().apply {
           add(observable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(object : DisposableObserver<ApiBase<ArrayList<TodayStock>>>(){
                   override fun onNext(t: ApiBase<ArrayList<TodayStock>>) {
                       t.retData.let { result ->
                           vmTodayStock._liveData.postValue(result)
                       }


                   }

                   override fun onError(e: Throwable?) {

                   }

                   override fun onComplete() {

                   }
               }))
       }


    }


}