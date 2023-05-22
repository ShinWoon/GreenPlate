package com.ssafy.green_plate.util

import com.ssafy.green_plate.api.ProductService
import com.ssafy.green_plate.api.UserService
import com.ssafy.green_plate.config.ApplicationClass

class RetrofitUtil {
    companion object{
//        val commentService = ApplicationClass.retrofit.create(CommentService::class.java)
//        val orderService = ApplicationClass.retrofit.create(OrderService::class.java)
        val productService = ApplicationClass.retrofit.create(ProductService::class.java)
        val userService = ApplicationClass.retrofit.create(UserService::class.java)
    }
}