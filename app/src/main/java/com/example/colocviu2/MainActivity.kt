package com.example.colocviu2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.colocviu2.ui.theme.Colocviu2Theme

class MainActivity : ComponentActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Colocviu2Theme {
                // Define the Scaffold to provide structure to the UI
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { padding ->
                        MapViewContainer(modifier = Modifier.padding(padding).fillMaxSize())
                    }
                )
            }
        }

        // Initialize the MapView
        mapView = MapView(this).apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MainActivity)
        }
    }

    @Composable
    fun MapViewContainer(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        var mapInitialized by remember { mutableStateOf(false) }

        AndroidView(
            factory = { mapView },
            modifier = modifier,
            update = { mapView ->
                if (!mapInitialized) {
                    mapView.onStart()
                    mapView.onResume()
                    lifecycle.addObserver(LifecycleEventObserver { _, event ->
                        when (event) {
                            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                            Lifecycle.Event.ON_STOP -> mapView.onStop()
                            Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                            else -> {}
                        }
                    })
                    mapInitialized = true
                }
            }
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Coordinates for Ghelmegioaia, Romania
        val ghelmegioaia = LatLng(44.6123, 22.8314) // Approximate coordinates
        mMap.addMarker(MarkerOptions().position(ghelmegioaia).title("Marker in Ghelmegioaia"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ghelmegioaia, 10F))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
