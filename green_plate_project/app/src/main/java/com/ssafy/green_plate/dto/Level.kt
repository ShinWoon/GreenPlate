package com.ssafy.green_plate.dto

data class Level(
    var title: String,
    var greetings : String,
    var unit: Int,
    var max: Int,
    var img: String
){
    companion object{
//        val levelTitleArr = arrayOf("씨앗", "꽃", "열매", "커피콩", "커피나무")
//
//        var userInfoList = arrayOf(
//            Level(levelTitleArr[0], 10, 50, "seeds.png"),
//            Level(levelTitleArr[1], 15, 125, "flower.png"),
//            Level(levelTitleArr[2], 20, 225, "coffee_fruit.png"),
//            Level(levelTitleArr[3], 25, 350, "coffee_beans.png"),
//            Level(levelTitleArr[4], Int.MAX_VALUE, Int.MAX_VALUE, "coffee_tree.png")
//        )
    }
}