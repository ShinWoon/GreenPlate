package com.ssafy.green_plate.dto

class Coupon (
    val id : Int,
    val userId : String,
    val type : String,
    val discountAmount : Int
) {
    constructor() : this(0,"","",0)

    override fun toString(): String {
        return "[${type}] ${discountAmount}원 할인 쿠폰"
    }
}

