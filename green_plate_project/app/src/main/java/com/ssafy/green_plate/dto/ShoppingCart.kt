package com.ssafy.green_plate.dto

data class ShoppingCart(
    var productId: Int,
    var productImg : String,
    var productName: String,
    var menuCnt: Int,
    var dressingId : Int,
    var addedStuff : List<Product>,
    var productPrice: Int,
    var totalPrice: Int = menuCnt*productPrice,
    var type: String
){
    constructor() : this(0, "","", 1, 0,  arrayListOf(),0, 0, "")
}