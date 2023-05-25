package com.ssafy.green_plate.src.main.mypage

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentStoreBinding
import com.ssafy.green_plate.dto.Store
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.MainActivityViewModel

private const val TAG = "StoreFragment"
class StoreFragment : BaseFragment<FragmentStoreBinding>(
    FragmentStoreBinding::bind,
    R.layout.fragment_store
) {

    private val storeViewModel: StoreViewModel by activityViewModels()
    private val activityViewModel : MainActivityViewModel by activityViewModels()

    lateinit var mainActivity: MainActivity
    private var lat = 0.0
    private var lng = 0.0


    private val locationManager by lazy {
        mainActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideBottomNav(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlaceData()
        lat = storeViewModel.lat
        lng = storeViewModel.lng
        Log.d(TAG, "onViewCreated: ${lat} ${lng}")
        storeViewModel.getStoreInfo()
        storeViewModel.storeInfo.observe(viewLifecycleOwner) {
            var storeList = it
            storeList.forEach {
                it.distance = getDistance(it.latitude, it.longitude)
            }
            binding.storePageRv.apply {
                adapter = StoreAdapter(requireContext(), storeList)
                layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    // 현재 위치와 매장 거리 계산
    private fun getDistance(latStore : Double, lngStore : Double) : Int {

        val dLat = Math.toRadians(latStore - lat)
        val dLng = Math.toRadians(lngStore - lng)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat)) * Math.cos(
            Math.toRadians(latStore)
        ) * Math.sin(dLng / 2) * Math.sin(dLng / 2)

        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
        val d = EARTH_RADIUS * c * 1000
        return d.toInt()
    }


    private fun setPlaceData() {
        if (activityViewModel.permissionCheck) {
            // 위치 권한이 있는 경우
            setLastLocation()
            getProvider()
        }
    }

    @SuppressLint("MissingPermission")
    private fun setLastLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val lastKnownLocation =
                locationManager.getLastKnownLocation(LocationManager.FUSED_PROVIDER)
            if (lastKnownLocation != null) {
                storeViewModel.lat = lastKnownLocation.latitude
                storeViewModel.lng = lastKnownLocation.longitude
                Log.d(
                    TAG,
                    "latitude=${lastKnownLocation.latitude}, longitude=${lastKnownLocation.longitude}"
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getProvider() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            locationManager.requestLocationUpdates(
                LocationManager.FUSED_PROVIDER,
                0,
                0f,
                listener
            )
        }
    }

    private val listener = object : LocationListener {
        @SuppressLint("SetTextI18n")
        override fun onLocationChanged(location: Location) {
            storeViewModel.lat = location.latitude
            storeViewModel.lng = location.longitude
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    companion object {
        private val EARTH_RADIUS = 6371
    }
}