package com.ssafy.green_plate.dto

class Product(
    val id : Int,
    val name : String,
    val englishName : String,
    val type : String,
    val discription : String,
    val price : Int,
    val img : String
) {
    constructor() : this(0,"","", "","",0,"")

    override fun toString(): String {
        return "[ id = $id, name = $name, englishName = $englishName, type = $type, discription = $discription, price = $price, img = $img]"
    }
}