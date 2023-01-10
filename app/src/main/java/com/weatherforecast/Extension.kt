package com.weatherforecast

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private val ioScope = CoroutineScope(Dispatchers.IO + Job())

fun AppCompatActivity.checkPermission(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED
}

fun AppCompatActivity.toast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun<T:Any> Flow<T>.collectNonBlocking(action:()-> Unit){
    ioScope.launch {
        collectLatest {
            action.invoke()
        }
    }
}