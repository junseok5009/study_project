package com.hjs.study.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.hjs.study.R
import com.hjs.study.databinding.ItemMainRcvBinding
import com.hjs.study.databinding.ItemTodayStockBinding
import com.hjs.study.model.TodayStock
import com.hjs.study.model.User
import com.hjs.study.util.BindingViewHolder

class TodayStockRcvAdapter(var data: LiveData<ArrayList<TodayStock>>) : RecyclerView.Adapter<BindingViewHolder<ItemTodayStockBinding>>()  {

    private val arr_r_today_stock_title_background : Array<Int> = arrayOf(
        R.drawable.boundary_round_today_stock_one,
        R.drawable.boundary_round_today_stock_two,
        R.drawable.boundary_round_today_stock_three,
        R.drawable.boundary_round_today_stock_four,
        R.drawable.boundary_round_today_stock_five
    )

    private val arr_r_today_stock_title_text_color : Array<Int> = arrayOf(
        Color.parseColor("#b29bff"),
        Color.parseColor("#76c2ff"),
        Color.parseColor("#fcb653"),
        Color.parseColor("#4ed79d"),
        Color.parseColor("#e091c9")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemTodayStockBinding> {
        val binding: ItemTodayStockBinding = ItemTodayStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemTodayStockBinding>, position: Int) {
        holder.binding().todayStock = data.value?.get(position)
        val resource_count = position % 5
        holder.binding().itemTodayStockTvBig.setTextColor(arr_r_today_stock_title_text_color[resource_count])
        holder.binding().itemTodayStockTvBig.setBackgroundResource(arr_r_today_stock_title_background[resource_count])
        holder.binding().root.setOnClickListener {
            Toast.makeText(holder.getBindRootContext(), "나 눌러써? p : $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }



}