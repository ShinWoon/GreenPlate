package com.ssafy.green_plate.dto

class User (val id : String, val name : String, val pass : String, val stamps : Int) {
    constructor():this("", "","",0)
    constructor(id:String, pass:String):this(id, "",pass,0)
}