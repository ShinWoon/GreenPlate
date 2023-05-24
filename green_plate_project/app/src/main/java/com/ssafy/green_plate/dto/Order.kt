package com.ssafy.green_plate.dto

import java.util.ArrayList

data class Order (
    var id: Int,
    var userId: String,
    var orderTable: String,
    var orderTime: String,
    var complited: String,
    var payType : String = "신용카드",
    var totalOrderPrice : Int = 0,
    var discountAmount:Int = 0,
    var storeName : String = "구미 싸피점",
    val details: ArrayList<OrderDetail> = ArrayList() ){

    constructor():this(0,"","","","","",0)
}
