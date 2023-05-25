package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemStorePageBinding
import com.ssafy.green_plate.dto.Store
import java.io.IOException
import java.util.Locale

private const val TAG = "StoreAdapter"
class StoreAdapter(val context: Context, private var items: List<Store>): RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    inner class StoreViewHolder(private val binding: ListItemStorePageBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : Store) {
                val location = Location("")
                location.latitude = data.latitude
                location.longitude = data.longitude
                binding.apply {
                    storeInfoNameTv.text = data.name
                    storePhoneNumTv.text = data.phoneNum
                    storeAddressTv.text = getCurrentAddress(location)
                    Log.d(TAG, "bindInfo: ${storeAddressTv.text}")
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ListItemStorePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        var item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getCurrentAddress(location: Location): String {
        //지오코더: GPS를 주소로 변환
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>?
        try {
            addresses = geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1
            )
        } catch (ioException: IOException) {
            //네트워크 문제
            Toast.makeText(context, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show()
            return "지오코더 사용불가"
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(context, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show()
            return "잘못된 GPS 좌표"
        }

        return if (addresses == null || addresses.isEmpty()) {
//            Toast.makeText(this, "주소 발견 불가", Toast.LENGTH_LONG).show()
            "주소 발견 불가"
        } else {
            val address = addresses[0]
            address.getAddressLine(0).toString()
        }
    }
}