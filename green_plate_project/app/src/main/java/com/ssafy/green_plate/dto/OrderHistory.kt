package com.ssafy.green_plate.dto

import java.util.*

data class OrderHistory (
    var productName: String,
    var productPrice : Int,
    var menuCnt: Int,
    var dressingName : String,
    var addedStuff : List<AddedStuffInfo>,
    var totalPrice : Int,
    var discountPrice : Int,
    var storeName : String,
    var orderDate : Date,
    var img : String,
    var totalMenu : Int,
    var orderId : Int,
    var payType : String,
    var completed : Char
    ) {
    constructor() : this("",0,0,"", arrayListOf(),0,0, "", Date(), "", 0,0, "", 'N')
}