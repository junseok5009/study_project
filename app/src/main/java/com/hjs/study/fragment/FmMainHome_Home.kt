package com.hjs.study.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import com.hjs.study.R
import com.hjs.study.activity.sta_tag
import com.hjs.study.adapter.TodayStockRcvAdapter
import com.hjs.study.databinding.FmMainHomeHomeBinding
import com.hjs.study.fragment.main.FmMainHome
import com.hjs.study.http.NetManager
import com.hjs.study.http.Services
import com.hjs.study.model.ApiBase
import com.hjs.study.model.TodayStock
import com.hjs.study.tr.TR_SIGNAL05
import com.hjs.study.viewmodel.VmTodayStock
import com.hjs.study.viewmodel.Vm_TR_SIGNAL05
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import pyxis.uzuki.live.rollingbanner.RollingViewPagerAdapter

class FmMainHome_Home :Fragment(){

    private lateinit var binding: FmMainHomeHomeBinding
    private lateinit var fragActivity: Activity
    private val mld_al_todayStock = MutableLiveData<ArrayList<TodayStock>>()

    private lateinit var vmTodayStock: VmTodayStock
    private lateinit var vmTrSignal05: Vm_TR_SIGNAL05

    init {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            fragActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmTodayStock = ViewModelProvider(this).get(VmTodayStock::class.java)
        vmTrSignal05 = ViewModelProvider(this).get(Vm_TR_SIGNAL05::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FmMainHomeHomeBinding.inflate(LayoutInflater.from(context), container, false)
        binding.activity = this

        binding.fmMHHTodayStockRcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        setListener()
        setObserve()

        request_TODAY01()
        request_SIGNAL05()



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

    private fun setListener(){

        binding.fmMHHSrl.setOnRefreshListener {
            request_TODAY01()
            binding.fmMHHSrl.isRefreshing = false
        }
                                                                                                        // error !!!!
        binding.fmMHHRollingBanner.setAdapter( object: RollingViewPagerAdapter<String>(fragActivity, ArrayList<String>() ) {
            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                super.destroyItem(container, position, `object`)
            }

            override fun getCount(): Int {
                return super.getCount()
            }

            override fun getView(var1: Int, item: String): View {
                TODO("Not yet implemented")
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                return super.instantiateItem(container, position)
            }

            override fun isViewFromObject(pager: View, obj: Any): Boolean {
                return super.isViewFromObject(pager, obj)
            }
        } )

    }

    private fun setObserve(){

        val dataObserver: Observer<ArrayList<TodayStock>> = Observer{ data ->
            mld_al_todayStock.value = data
            binding.fmMHHTodayStockRcv.adapter = TodayStockRcvAdapter(mld_al_todayStock)
        }

        vmTodayStock.liveData.observe(viewLifecycleOwner, dataObserver)

    }

    private fun request_TODAY01(){
        val jsonObj = JsonObject().apply {
            addProperty("userId", "0000179986@sa")
        }
        val services: Services = NetManager.rxJavaRetrofit.create(Services::class.java)
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

    private fun request_SIGNAL05(){
        val jsonObj = JsonObject().apply {
            addProperty("userId", "0000179986@sa")
        }

        val services: Services = NetManager.rxJavaRetrofit.create(Services::class.java)
        val observable = services.getSignal05(jsonObj)

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<ApiBase<TR_SIGNAL05>>(){
                override fun onNext(t: ApiBase<TR_SIGNAL05>) {
                    binding.vTrSignal05ApiBase = t
                    if (t.retCode.equals("0000") && t.retMsg.equals("success")){
                        t.retData.let { result ->
                            binding.vTrSignal05 = result
                            vmTrSignal05._liveData.postValue(result)
                            vmTrSignal05.addDisposable(this)
                        }
                    }else{

                    }

                }

                override fun onError(e: Throwable?) {

                }

                override fun onComplete() {

                }
            })


        /*CompositeDisposable().apply {
            add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ApiBase<ArrayList<TodayStock>>>(){
                    override fun onNext(t: ApiBase<ArrayList<TodayStock>>) {
                        t.retData.let { result ->
                            vmTodayStock._liveData.postValue(result)
                            vmTodayStock.addDisposable(this)
                        }


                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onComplete() {

                    }
                }))
        }*/


    }

    fun onClick_ai_signal_all_see(view: View){
        FmMainHome.binding.fmMainHomeTably.getTabAt(1)?.select()
    }


}