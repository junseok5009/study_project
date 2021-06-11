package com.hjs.study.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.hjs.study.databinding.ItemMainRcvBinding
import com.hjs.study.model.User


class RecyclerAdapter(var data: LiveData<ArrayList<User>>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMainRcvBinding = ItemMainRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data.value!![position])

    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }


    /*원래 viewholder
    (private val binding: ItemMainRcvBinding, itemView: View) : RecyclerView.ViewHolder(itemView)
    view bind viewholder class*/
    inner class ViewHolder(private val binding: ItemMainRcvBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(al_User: User){

            //기존이라면 이렇게 했겠지만
            //binding.itemMainRcvTv1.text = ""

            // apply 함수로 binding 뒤에 오는 애들 묶어서 처리 쌉가능
            binding.apply {
                itemMainRcvTv1.text = al_User.name
                itemMainRcvTv2.text = al_User.nickname
            }

        }

    }

}