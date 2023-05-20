package com.ssafy.green_plate.dto

class Coupon (
    val id : Int,
    val userId : String,
    val couponType : String,
    val discountAmount : Int
) {
    constructor() : this(0,"","",0)
}

