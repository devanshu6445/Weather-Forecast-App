package com.weatherforecast

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherforecast.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var viewModel: MainViewModel? = null
    private var locationManager:LocationManager? = null
    private val adapter:WeatherAdapter = WeatherAdapter()
    private val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permission ->
        var isGranted = true
        permission.entries.forEach {
            if(!it.value){ isGranted = false}
        }

        if (isGranted){
            val location = locationManager?.let { it1 -> getCurrentCoordinates(it1) }
            lifecycleScope.launch {
                viewModel?.getWeatherDetails(location)?.collectLatest {
                    adapter.submitData(it)
                }
            }
        }else {
            toast("Please grant the permission!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            val location = getCurrentCoordinates(locationManager!!)
            lifecycleScope.launch {
                val f = viewModel?.getWeatherDetails(location)?.collectLatest {
                    adapter.submitData(it)
                }
            }
        } else {
            permissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

        binding?.cityRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentCoordinates(locationManager: LocationManager): Location? {
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    }
}