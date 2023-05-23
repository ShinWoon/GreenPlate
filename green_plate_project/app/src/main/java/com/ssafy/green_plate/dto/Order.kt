package com.ssafy.green_plate.dto

import java.util.ArrayList

data class Order (
    var id: Int,
    var userId: String,
    var orderTable: String,
    var orderTime: String,
    var complited: String,
    var payType : String = "신용카드",
    val details: ArrayList<OrderDetail> = ArrayList() ){


    var discountAmount:Int = 0
//    var payType : String = "신용카드"
    var storeName : String = "구미 싸피점"


    constructor():this(0,"","","","","")
}
