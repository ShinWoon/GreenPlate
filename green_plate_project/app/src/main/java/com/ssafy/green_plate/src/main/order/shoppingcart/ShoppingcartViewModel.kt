package com.ssafy.green_plate.src.main.order.shoppingcart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.dto.OrderDetail
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

class ShoppingcartViewModel : ViewModel() {
//    fun makeOrder(orderInfo: MutableList<OrderDetail>, orderTable : String) {
//        val user = ApplicationClass.sharedPreferencesUtil.getUser()
//        val order = Order(0, user.id, orderTable, "", "", orderInfo as ArrayList<OrderDetail>)
//        Log.d(TAG, "makeOrder: $orderInfo")
//        Log.d(TAG, "makeOrder: $order")
//
//        viewModelScope.launch {
//            try {
//                RetrofitUtil.orderService.makeOrder(order)
//            } catch (e: Exception) {
//
//            }
//        }
//    }
}