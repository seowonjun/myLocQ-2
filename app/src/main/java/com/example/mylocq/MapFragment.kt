package com.example.mylocq

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class MapFragment : Fragment(), OnMapReadyCallback {


    var mainActivity: MainActivity? = null
    lateinit var rootView : View

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_map, container, false)
        mView = rootView.findViewById(R.id.mapView) as MapView
        mView.onCreate(savedInstanceState)

        mView.getMapAsync(this)


        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f))

        rootView.findViewById<Button>(R.id.goToStreet).setOnClickListener {
            myLocation = googleMap.cameraPosition.target
            mainActivity?.setFragment2() }
        val marker = MarkerOptions()
            .position(myLocation)
            .title("Nowon")
            .snippet("노원역")
        googleMap.addMarker(marker)
    }

    override fun onStart(){
        super.onStart()
        mView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mView.onLowMemory()
    }

    override fun onDestroy() {
        mView.onDestroy()
        super.onDestroy()
    }



}