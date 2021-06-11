package com.hjs.study.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjs.study.R
import com.hjs.study.adapter.RecyclerAdapter
import com.hjs.study.databinding.ActivityMainBinding
import com.hjs.study.model.User
import com.hjs.study.viewmodel.MainViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainKtActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var data = MutableLiveData<ArrayList<User>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainRcv.setLayoutManager(LinearLayoutManager(this))
        binding.mainBtn.setOnClickListener(View.OnClickListener {
            //val al_user = ArrayList<User>()
            ArrayList<User>().apply {
                add(User("정대현", "여왕벌"))
                add(User("오승환", "끝판왕"))
                add(User("정우람", "마무리"))
            }.let {
                al_user ->  viewModel.liveData.postValue(al_user)
            }

        })

        val dataObserver: Observer<ArrayList<User>> =
            Observer { livedata ->
                    data.value = livedata
                    val newAdapter = RecyclerAdapter(data)
                binding.mainRcv.adapter = newAdapter
            }
        viewModel.liveData.observe(this, dataObserver)

    }



}