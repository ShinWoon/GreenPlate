package com.ssafy.green_plate.dto

data class Grade (
    val img: String,
    val max: Int,
    val greetings: String,
    val title: String
){
    constructor():this("",0,"","")
}