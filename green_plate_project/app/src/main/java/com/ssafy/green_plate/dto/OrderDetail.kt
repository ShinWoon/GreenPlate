package com.ssafy.green_plate.dto

data class OrderDetail(val id : Int, val orderId : Int, val productId : Int, val dressingId : Int,
val addedStuff: String, val addedQuantity: String, val quantity : Int) {
    constructor(productId: Int, dressingId:Int, addedStuff: String) : this(0, 0, productId, dressingId, addedStuff, "", 1)
}