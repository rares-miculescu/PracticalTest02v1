package com.example.colocviu2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.colocviu2.ui.theme.Colocviu2Theme


class MainActivity : ComponentActivity() {
    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap

    private lateinit var editTextQuery: EditText
    private lateinit var textViewResults: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        editTextQuery = findViewById(R.id.editTextQuery)
        textViewResults = findViewById(R.id.textViewResults)

        // Adaugă un listener pentru a actualiza TextView în timp real pe măsură ce utilizatorul scrie
        editTextQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textViewResults.text = s?.toString() ?: ""
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Aici nu trebuie să facem nimic
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Aici nu trebuie să facem nimic
            }
        })
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Colocviu2Theme {
//                // NavController setup
//                val navController = rememberNavController()
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    content = { padding ->
//                        NavHost(navController = navController, startDestination = "mapView") {
//                            composable("mapView") {
//                                MapViewContainer(
//                                    modifier = Modifier.padding(padding).fillMaxSize(),
//                                    navController = navController
//                                )
//                            }
//                            composable("textView") {
//                                TextViewScreen(modifier = Modifier.padding(padding).fillMaxSize())
//                            }
//                        }
//                    }
//                )
//            }
//        }
//
//        // Initialize the MapView
//        mapView = MapView(this).apply {
//            onCreate(savedInstanceState)
//            getMapAsync(this@MainActivity)
//        }
//    }

//    @Composable
//    fun MapViewContainer(modifier: Modifier = Modifier, navController: androidx.navigation.NavController) {
//        val context = LocalContext.current
//        val lifecycle = LocalLifecycleOwner.current.lifecycle
//        var mapInitialized by remember { mutableStateOf(false) }
//
//        Column(modifier = modifier) {
//            AndroidView(
//                factory = { mapView },
//                update = { mapView ->
//                    if (!mapInitialized) {
//                        mapView.onStart()
//                        mapView.onResume()
//                        lifecycle.addObserver(LifecycleEventObserver { _, event ->
//                            when (event) {
//                                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
//                                Lifecycle.Event.ON_STOP -> mapView.onStop()
//                                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
//                                else -> {}
//                            }
//                        })
//                        mapInitialized = true
//                    }
//                }
//            )
//            Button(onClick = { navController.navigate("textView") }, modifier = Modifier.padding(16.dp)) {
//                Text("Go to TextView")
//            }
//        }
//    }

//    @Composable
//    fun TextViewScreen(modifier: Modifier = Modifier) {
//        Text("This is the TextView Screen", modifier = modifier.padding(16.dp))
//    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        // Coordinates for Ghelmegioaia, Romania
//        val ghelmegioaia = LatLng(44.6123, 22.8314) // Approximate coordinates
//        mMap.addMarker(MarkerOptions().position(ghelmegioaia).title("Marker in Ghelmegioaia"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ghelmegioaia, 10F))
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mapView.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mapView.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mapView.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView.onDestroy()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        mapView.onSaveInstanceState(outState)
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView.onLowMemory()
//    }
}
