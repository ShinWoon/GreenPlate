package com.ssafy.green_plate.api

import com.ssafy.green_plate.dto.Order
import com.ssafy.green_plate.models.MenuDetailWithProductInfo
import com.ssafy.green_plate.models.OrderDetailResponse
import retrofit2.http.*

interface OrderService {
    // order 객체를 저장하고 추가된 Order의 id를 반환한다.
    @POST("rest/order")
    suspend fun makeOrder(@Body body: Order): Int
//
//    // {orderId}에 해당하는 주문의 상세 내역을 목록 형태로 반환한다.
//    // 사용자 정보 화면의 주문 내역 조회에서 사용된다.
    @GET("rest/order/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Int): List<OrderDetailResponse>


    @GET("rest/order/byUser")
    suspend fun getLatestOrder(@Query("id") id: String): List<MenuDetailWithProductInfo>

    // {id}에 해당하는 사용자의 최근 1개월간 주문 내역을 반환한다.
    // 반환 정보는 1차 주문번호 내림차순, 2차 주문 상세 내림차순으로 정렬된다.
    @GET("rest/order/getMonthOrder")
    suspend fun getMonthOrder(@Query("id") id: String): List<MenuDetailWithProductInfo>

    // {orderId}에 해당하는 complete를 'Y'로 업데이트 해준다.
    @GET("rest/order/orderCompleted/{orderId}")
    suspend fun updateOrderCompleted(@Path("orderId") orderId: Int) : Int
}