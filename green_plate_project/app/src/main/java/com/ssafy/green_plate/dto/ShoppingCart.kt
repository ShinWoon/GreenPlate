package com.ssafy.green_plate.dto

data class ShoppingCart(
    val menuId: Int,
    val menuImg: String,
    val menuName: String,
    var menuCnt: Int,
    var dressingId : Int,
    val addedStuff : String,
    val addedQuantity : String,
    val menuPrice: Int,
    var totalPrice: Int = menuCnt*menuPrice,
    val type: String
){
    constructor() : this(0, "", "", 0, 0, "", "", 0, 0, "")
}