package com.ssafy.green_plate.src.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.dto.Coupon
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

class CouponViewModel : ViewModel() {
    private var _couponList = listOf<Coupon>()
    val couponList : List<Coupon>
        get() = _couponList

    fun getCouponList(id : String) {
        viewModelScope.launch {
            try {
                _couponList = RetrofitUtil.couponService.getCoupon(id)
            } catch (e : Exception) {

            }
        }
    }
}