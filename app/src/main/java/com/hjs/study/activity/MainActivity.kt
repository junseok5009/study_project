package com.hjs.study.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.JsonObject
import com.hjs.study.R
import com.hjs.study.databinding.ActivityMainBinding
import com.hjs.study.fragment.main.FmMainHome
import com.hjs.study.fragment.main.FmMainMy
import com.hjs.study.fragment.main.FmMainNoti
import com.hjs.study.fragment.main.FmMainSearch
import com.hjs.study.http.NetManager
import com.hjs.study.http.Services
import com.hjs.study.model.ApiBase
import com.hjs.study.model.TodayStock
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainNv.itemIconTintList = null
        binding.mainNv.setOnNavigationItemSelectedListener(BottomNavigationItemSeletedListener())
        //binding.mainVp2.adapter = MainVp2Adapter(supportFragmentManager, lifecycle)
        //binding.mainVp2.registerOnPageChangeCallback(ViewPagerChangeCallback())

        supportFragmentManager.beginTransaction().add(R.id.main_frame_layout, FmMainHome()).commitNow()
        request_http_web_user_info()



    }

    private fun request_http_web_user_info(){
        val param = "userid=0000182641@gl"
        val services: Services = NetManager.getRxJavaRtrofitWeb().create(Services::class.java)
        services.getUserInfoProcAjax(param).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(sta_tag, "request_http_web_user_info() onResponse r : ${response}")
                Log.d(sta_tag, "request_http_web_user_info() onResponse r body : ${response.body()}")
                Log.d(sta_tag, "request_http_web_user_info() onResponse r code : ${response.code()}")
                Log.d(sta_tag, "request_http_web_user_info() onResponse r errorBody : ${response.errorBody()}")
                Log.d(sta_tag, "request_http_web_user_info() onResponse r headers : ${response.headers()}")
                Log.d(sta_tag, "request_http_web_user_info() onResponse r message : ${response.message()}")
                Log.d(sta_tag, "request_http_web_user_info() onResponse r raw : ${response.raw()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(sta_tag, "request_http_web_user_info() onResponse r : ${t.message}")
            }
        })

    }

    private inner class MainVp2Adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){
        override fun getItemCount(): Int {
            return 4
        }
        override fun createFragment(position: Int): Fragment {
            return when (position){
                0 -> FmMainHome()
                1 -> FmMainSearch()
                2 -> FmMainNoti()
                3 -> FmMainMy()
                else -> FmMainHome()
            }
        }
    }

    private inner class ViewPagerChangeCallback: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            binding.mainNv.selectedItemId = when(position){
                0 -> R.id.menu_btm_navi_home
                1 -> R.id.menu_btm_navi_search
                2 -> R.id.menu_btm_navi_noti
                3 -> R.id.menu_btm_navi_my
                else -> R.id.menu_btm_navi_home

            }

        }
    }

    private inner class BottomNavigationItemSeletedListener: BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.menu_btm_navi_home -> {
                    //binding.mainVp2.currentItem = 0
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, FmMainHome()).commitAllowingStateLoss()
                    return true
                }
                R.id.menu_btm_navi_search -> {
                    //binding.mainVp2.currentItem = 1
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, FmMainSearch()).commitAllowingStateLoss()
                    return true
                }
                R.id.menu_btm_navi_noti -> {
                    //binding.mainVp2.currentItem = 2
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, FmMainNoti()).commitAllowingStateLoss()
                    return true
                }
                R.id.menu_btm_navi_my -> {
                    //binding.mainVp2.currentItem = 3
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, FmMainMy()).commitAllowingStateLoss()
                    return true
                }
            }
            return false
        }
    }

}