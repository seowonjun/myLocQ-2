package com.example.mylocq

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.StreetViewPanoramaView
import com.google.android.gms.maps.model.LatLng
import java.security.Permission
import java.util.jar.Manifest

lateinit var mView : MapView
lateinit var stView: StreetViewPanoramaView

var myLocation = LatLng(34.8559, 128.6960)


class MainActivity : AppCompatActivity() {

    private val multiplePremissionsCode = 100
    private val requiredPremissions = arrayOf(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()
        setFragment2()
    }
    fun setFragment(){
        val mapFragment : MapFragment = MapFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.framLayout, mapFragment)
        transaction.commit()
    }

    fun setFragment2(){
        val StreetFragment : StreetFragment = StreetFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.framLayout, StreetFragment)
        transaction.commit()
    }


    private fun checkPermissions(){
        val rejectedPermissionList = ArrayList<String>()

        for(permission in rejectedPermissionList){
            if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED)
                rejectedPermissionList.add(permission)
        }

        if(rejectedPermissionList.isEmpty()){
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(this, requiredPremissions, multiplePremissionsCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            multiplePremissionsCode -> {
                if (grantResults.isNotEmpty()) {
                    for ((i, permission) in permissions.withIndex()) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                            Log.i("Tag", "The user has denied to...")
                        }


                    }
                }
            }
        }
    }

}

