package com.hjs.study.fragment.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hjs.study.R
import com.hjs.study.activity.sta_tag
import com.hjs.study.databinding.FmMainHomeBinding
import com.hjs.study.databinding.ItemCustomTabBinding
import com.hjs.study.fragment.FmMainHome_Ai
import com.hjs.study.fragment.FmMainHome_Home
import com.hjs.study.fragment.FmMainHome_Market

class FmMainHome : Fragment(){

    // fmmainhome_home 에서 참조하기 위해서 (java의 static이랑 비슷, 자바파일에서 이거 참조하려면 @JvmStatic 이거 붙여야 함)
    companion object{
        lateinit var binding: FmMainHomeBinding
    }

    private val ar_tabTitle: Array<String> = arrayOf("홈","AI매매신호","마켓뷰")


    init {
        Log.d(sta_tag, "FmMainHome init()")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d(sta_tag, "FmMainHome onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(sta_tag, "FmMainHome onCreate()")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(sta_tag, "FmMainHome onCreateView()")
        binding = FmMainHomeBinding.inflate(LayoutInflater.from(context), container, false)

        binding.fmMainHomeVp.adapter = FmMainHomeVpAdapter( (context as FragmentActivity).supportFragmentManager , lifecycle)


        binding.fmMainHomeTably.apply {
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_CENTER
            isTabIndicatorFullWidth = false
        }

        TabLayoutMediator(binding.fmMainHomeTably, binding.fmMainHomeVp){
                tab: TabLayout.Tab, position: Int ->

            /*tab.text = ar_tabTitle[position]
            tab.icon = AppCompatResources.getDrawable(context as FragmentActivity, R.drawable.circle_maincolor)
            val tabUnSelectIconColor: Int = ContextCompat.getColor(context as FragmentActivity, R.color.white)
            tab.icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(tabUnSelectIconColor, BlendModeCompat.SRC_IN)*/
            DataBindingUtil.bind<ItemCustomTabBinding>(
                LayoutInflater.from(context).inflate(R.layout.item_custom_tab, null)
            )?.apply {
                itemCustomTabTv.text = ar_tabTitle[position]
                when(position){
                    0 -> {
                        itemCustomTabIv.visibility = View.VISIBLE
                    }
                    1, 2 -> {
                        itemCustomTabIv.visibility = View.INVISIBLE
                    }
                }
            }?.run {
                tab.customView = this@run.root
            }

        }.attach()

        binding.fmMainHomeTably.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val iv: ImageView ?=  tab?.customView?.findViewById(R.id.item_custom_tab_iv)
                iv?.let {
                    iv.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val iv: ImageView ?=  tab?.customView?.findViewById(R.id.item_custom_tab_iv)
                iv?.let {
                    iv.visibility = View.INVISIBLE
                }
                //val tabUnSelectIconColor: Int = ContextCompat.getColor(context as FragmentActivity, R.color.white)
                //tab!!.icon!!.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(tabUnSelectIconColor, BlendModeCompat.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })



        //setTabs()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d(sta_tag, "FmMainHome onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(sta_tag, "FmMainHome onPause()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(sta_tag, "FmMainHome onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(sta_tag, "FmMainHome onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(sta_tag, "FmMainHome onDetach()")
    }

    private inner class FmMainHomeVpAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){
        override fun getItemCount(): Int {
            return 3
        }
        override fun createFragment(position: Int): Fragment {
            return when (position){
                0 -> FmMainHome_Home()
                1 -> FmMainHome_Ai()
                2 -> FmMainHome_Market()
                else -> FmMainHome_Home()
            }
        }
    }

    private inner class ViewPagerChangeCallback: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            binding.fmMainHomeTably.selectTab(binding.fmMainHomeTably.getTabAt(position))

        }

    }


}