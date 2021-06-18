package com.hjs.study.etc

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.hjs.study.R
import com.hjs.study.databinding.ItemMainRcvBinding
import com.hjs.study.databinding.ItemPermissionDialogBinding
import java.lang.ClassCastException

// item layout이 relative layout이면 match match

class PermissionDialog: DialogFragment() {

    lateinit var activity: Activity
    var listener: OnPermissionDialogClickListener ?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnPermissionDialogClickListener){
            listener = context
        }
        if(context is Activity){
            activity = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ItemPermissionDialogBinding.inflate(LayoutInflater.from(activity), container, false).apply {
            itemPermissionDialogCl.setOnClickListener {
                listener?.onPermissionDialogClick()
                dismiss()
            }
            itemPermissionDialogTv.setOnClickListener {
                listener?.onPermissionDialogClick()
                dismiss()
            }
            return root
        }
        //val view : View = inflater.inflate(R.layout.item_permission_dialog, container, false)
        /*binding.itemPermissionDialogCl.setOnClickListener {
            listener?.onPermissionDialogClick()
            dismiss()
        }
        binding.itemPermissionDialogTv.setOnClickListener{
            listener?.onPermissionDialogClick()
            dismiss()
        }*/

        //return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(activity, theme) {
            override fun onBackPressed() {
                Toast.makeText(activity, "뒤로가기 누름?", Toast.LENGTH_LONG).show()
            }
        }.apply {
            //window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    interface OnPermissionDialogClickListener{
        fun onPermissionDialogClick()
    }

}