package com.ssafy.green_plate.dto

data class Store (
    var id : Int,
    var name : String,
    var latitude : Double,
    var longitude: Double,
    var phoneNum : String,
    var distance : Int
        ){
    constructor() : this(0,"",0.0,0.0,"",0)
}