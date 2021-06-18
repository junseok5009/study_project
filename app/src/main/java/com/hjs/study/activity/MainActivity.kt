package com.hjs.study.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hjs.study.R
import com.hjs.study.databinding.ActivityMainBinding
import com.hjs.study.fragment.main.FmMainHome
import com.hjs.study.fragment.main.FmMainMy
import com.hjs.study.fragment.main.FmMainNoti
import com.hjs.study.fragment.main.FmMainSearch

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