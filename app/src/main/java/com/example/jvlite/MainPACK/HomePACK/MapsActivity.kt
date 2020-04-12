package com.example.jvlite.MainPACK.HomePACK

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.jvlite.R
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.Place.TYPE_COUNTRY
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private var AUTOCOMPLETE_REQUEST_CODE = 1;
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        autoCompleteSessionToken()
        setupAutoCompleteFragment()

    }
    private fun autoCompleteSessionToken(){
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyBt9JGHKlo8chWR4LF3M075AuLHlHX3BvE" )
        }

        val placesClient: PlacesClient = Places.createClient(this)
        val token = AutocompleteSessionToken.newInstance()
        val bounds = RectangularBounds.newInstance(
            LatLng(-33.880490, 151.184363),
            LatLng(-33.858754, 151.229596)
        )
        val request =
            FindAutocompletePredictionsRequest.builder()
                .setLocationBias(bounds)
                .setCountry("vn")
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .build()
        placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
            for (prediction in response.autocompletePredictions) {

            }
        }.addOnFailureListener { exception ->
            if (exception is ApiException) {
            }
        }
    }

    private fun setupAutoCompleteFragment() {
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?

        autocompleteFragment?.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        autocompleteFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
            }

            override fun onError(p0: Status) {
                Log.e("Error", p0.statusMessage);
            }

        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        var khoaPham = LatLng(10.763435, 106.663745)
        mMap.addMarker(MarkerOptions().position(khoaPham).title("Marker in Khoa Pham"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khoaPham, 18.5F))
    }

}
