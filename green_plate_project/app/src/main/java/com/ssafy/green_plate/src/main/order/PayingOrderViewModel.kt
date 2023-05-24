package com.ssafy.green_plate.src.main.order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.dto.Order
import com.ssafy.green_plate.dto.OrderDetail
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "ShoppingcartViewModel_싸피"
class PayingOrderViewModel : ViewModel() {
    fun makeOrder(orderInfo: MutableList<OrderDetail>, selectedCard: String, discountAmount: Int) {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        Log.d(TAG, "makeOrder: $selectedCard")
        val order = Order(0, user.id, "", "", "", "신용카드 - $selectedCard", discountAmount, "", orderInfo as ArrayList<OrderDetail>)
        Log.d(TAG, "makeOrder: $orderInfo")
        Log.d(TAG, "makeOrder: $order")

        viewModelScope.launch {
            try {
                RetrofitUtil.orderService.makeOrder(order)
            } catch (e: Exception) {

            }
        }
    }

    fun deleteCoupon(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitUtil.couponService.deleteCoupon(id.toString())
            }catch (e: Exception) {

            }
        }
    }
}