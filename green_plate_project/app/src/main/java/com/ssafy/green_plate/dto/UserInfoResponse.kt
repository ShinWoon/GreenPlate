package com.ssafy.green_plate.dto

data class UserInfoResponse(
    val grade: Grade,
    val user: User,
    val order: List<Order>
)