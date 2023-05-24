package com.ssafy.green_plate.src.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.dto.Coupon
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "CouponViewModel_싸피"

class CouponViewModel : ViewModel() {
    private var _couponList = MutableLiveData<List<Coupon>>(mutableListOf())
    val couponList: LiveData<List<Coupon>>
        get() = _couponList

    fun getCouponList() {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        viewModelScope.launch {
            try {
                _couponList.value = RetrofitUtil.couponService.getCoupon(user.id)
                Log.d(TAG, "getCouponList: $couponList")
            } catch (e: Exception) {

            }
        }
    }
}