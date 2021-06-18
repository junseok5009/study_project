package com.hjs.study.activity.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.hjs.study.R
import com.hjs.study.activity.MainActivity
import com.hjs.study.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this

        setPhoneNumberTextView()




    }

    fun setPhoneNumberTextView(){
        if(Build.VERSION.SDK_INT >= 30){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED){
                binding.loginTvId.text = ( this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager ).line1Number
            }
        }else{
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
                binding.loginTvId.text = ( this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager ).line1Number
            }
        }

    }

    fun btn_ok_onClick(view: View){
        Intent(this, MainActivity::class.java).apply {
            //addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            //addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }.let {
            startActivity(it)
            finish()
        }
    }



}