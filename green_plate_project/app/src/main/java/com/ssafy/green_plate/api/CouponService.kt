package com.ssafy.green_plate.api

import com.ssafy.green_plate.dto.Coupon
import com.ssafy.green_plate.dto.Order
import retrofit2.http.*

interface CouponService {

    @POST("rest/coupon")
    suspend fun addCoupon(@Body body: Coupon)
    @GET("rest/coupon/{userId}")
    suspend fun getCoupon(@Path("userId") userId: String) : List<Coupon>
    @DELETE("rest/coupon/{id}")
    suspend fun deleteCoupon(@Path("id") id: String)
}