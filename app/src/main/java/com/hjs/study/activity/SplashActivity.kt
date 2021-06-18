package com.hjs.study.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.hjs.study.R
import com.hjs.study.activity.login.LoginActivity
import com.hjs.study.etc.PermissionDialog
import java.util.*
import kotlin.collections.ArrayList


val sta_tag: String = "junseok"

class SplashActivity : AppCompatActivity(), PermissionDialog.OnPermissionDialogClickListener {

    private lateinit var deviceId: String
    private val permission_request_code: Int = 7
    private var permission_request_result: Boolean = false

    private lateinit var al_pList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkPermission()

    }

    override fun onPermissionDialogClick() {
        ActivityCompat.requestPermissions(this@SplashActivity, al_pList.toTypedArray(), permission_request_code)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(sta_tag, "onRequestPermissionsResult requestCode : "+requestCode)

        when(requestCode){
            permission_request_code -> {
                permission_request_result = true
                if(grantResults.size>0){
                    for(i in grantResults.indices){
                        Log.d(sta_tag, "onRequestPermissionsResult permissions ("+i+") str : "+permissions[i]+" / grant : "+grantResults[i])
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED && permissions[i] == "android.permission.READ_PHONE_STATE") {
                            permission_request_result = false
                        } else if (grantResults[i] == PackageManager.PERMISSION_DENIED && permissions[i] == "android.permission.READ_PHONE_NUMBERS") {
                            permission_request_result = false
                        }
                    }
                }

                if(permission_request_result){
                    getDevieId()
                }else{
                    Toast.makeText(this, "PERMISSION_DENIED", Toast.LENGTH_SHORT).show()
                    getDevieId()
                }
            }

        }
    }

    fun checkPermission(){
        Log.d(sta_tag, " checkPermission()")
        al_pList = ArrayList()

        //폰번호 가져오는 권한
        if(Build.VERSION.SDK_INT >= 30){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED){
                al_pList.add(Manifest.permission.READ_PHONE_NUMBERS)
            }
        }else{
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
                al_pList.add(Manifest.permission.READ_PHONE_STATE)
            }
        }

        if(al_pList.size > 0){
            showPermissionInfo()
        }else{
            getDevieId()
        }

    }

    private fun getDevieId(){
        Log.d(sta_tag, " getDevieId()")
        /*getAndroidId()?.let{ androidId ->
            deviceId = androidId
            return
        }
        deviceId = getUuId()*/

        getAndroidId()?.let { android ->
            deviceId = android
            Log.d(sta_tag, " android not null : "+deviceId)
        } ?: run{
            Log.d(sta_tag, " android null : "+deviceId)
            deviceId = getUuId()
        }

        //deviceId = if(getAndroidId()!=null) getAndroidId()!! else getUuId()

        Log.d(sta_tag, " deviceId : "+deviceId)

        Intent(this, LoginActivity::class.java).apply {
            //addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            //addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }.let {
            startActivity(it)
            finish()
        }

        //startActivity(Intent(this, LoginActivity::class.java).addFlags().addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION))
    }

    private fun getAndroidId(): String?{

        lateinit var android_id: String

        android_id = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)

        return android_id
    }

    private fun getUuId(): String{
        return UUID.randomUUID().toString()
    }

    private fun showPermissionInfo(){

        PermissionDialog().apply {
            //setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
            show(supportFragmentManager, "permission")
        }


    }

}