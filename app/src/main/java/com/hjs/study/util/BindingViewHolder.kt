package com.hjs.study.util

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class BindingViewHolder<T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: T

    init {
        //binding = ( DataBindingUtil.bind<ViewDataBinding>(view) ) as T=
        binding = DataBindingUtil.bind<ViewDataBinding>(view) as T
    }

    fun binding(): T {
        return binding
    }


}