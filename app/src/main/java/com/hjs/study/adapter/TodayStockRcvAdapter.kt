package com.hjs.study.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.hjs.study.databinding.ItemMainRcvBinding
import com.hjs.study.databinding.ItemTodayStockBinding
import com.hjs.study.model.TodayStock
import com.hjs.study.model.User
import com.hjs.study.util.BindingViewHolder

class TodayStockRcvAdapter(var data: LiveData<ArrayList<TodayStock>>) : RecyclerView.Adapter<BindingViewHolder<ItemTodayStockBinding>>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemTodayStockBinding> {
        val binding: ItemTodayStockBinding = ItemTodayStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemTodayStockBinding>, position: Int) {
        holder.binding().todayStock = data.value?.get(position)
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }



}