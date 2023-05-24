package com.ssafy.green_plate.api

import com.ssafy.green_plate.dto.Coupon
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CouponService {
    @GET("rest/coupon/{userId}")
    suspend fun getCoupon(@Path("userId") userId: String) : List<Coupon>

    @DELETE("rest/coupon/{id}")
    suspend fun deleteCoupon(@Path("id") id: String)
}