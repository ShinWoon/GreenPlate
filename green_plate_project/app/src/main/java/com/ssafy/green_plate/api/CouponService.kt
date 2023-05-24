package com.ssafy.green_plate.api

import com.ssafy.green_plate.dto.Coupon
import retrofit2.http.GET
import retrofit2.http.Query

interface CouponService {
    @GET("rest/coupon/")
    suspend fun getCoupon(@Query("id") id: String) : List<Coupon>
}