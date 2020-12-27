package com.example.mylocq

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.StreetViewSource
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StreetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StreetFragment :  Fragment(), OnStreetViewPanoramaReadyCallback {

    lateinit var geocoder: Geocoder
    var mainActivity: MainActivity? = null
    lateinit var rootView: View
    var random : Random = Random(System.currentTimeMillis())

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_street, container, false)

        stView = rootView.findViewById(R.id.StreetView) as StreetViewPanoramaView

        stView.onCreate(savedInstanceState)
        stView.getStreetViewPanoramaAsync(this)



        return rootView
    }

    override fun onStreetViewPanoramaReady(streetViewPanoramaView: StreetViewPanorama) {
        rootView.findViewById<Button>(R.id.goToRandom).setOnClickListener {

            if (streetViewPanoramaView.location != null) {
                myLocation = streetViewPanoramaView.location.position

            }
            rootView.findViewById<TextView>(R.id.addressView).text = ""
            var int1 = random.nextInt(180)
            var int2 = random.nextInt(360)
            var la : Double = (int1 - 90) + (random.nextInt()%100000)/100000.0
            var  lo : Double = (int2 - 180) + (random.nextInt()%100000)/100000.0
            Log.d("a3",la.toString())
            Log.d("l3", lo.toString())
            myLocation = LatLng(la, lo)




            streetViewPanoramaView.setPosition(myLocation, 2000000)

            //mainActivity?.setFragment()

        }

        rootView.findViewById<Button>(R.id.country).setOnClickListener {
            var list: List<Address>? = null
            geocoder = Geocoder(mainActivity)
            myLocation = streetViewPanoramaView.location.position
            rootView.findViewById<TextView>(R.id.addressView).text = geocoder.getFromLocation( myLocation.latitude, myLocation.longitude, 1).get(0).countryName
            Log.d("a2", myLocation.latitude.toString())
            Log.d("l2", myLocation.longitude.toString())
        }

        Log.d("a", myLocation.latitude.toString())
        Log.d("l", myLocation.longitude.toString())
        streetViewPanoramaView.setPosition(myLocation, 2000000, StreetViewSource.OUTDOOR)


    }

}