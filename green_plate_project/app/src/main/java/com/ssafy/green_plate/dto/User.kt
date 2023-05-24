package com.ssafy.green_plate.dto

class User (val id : String, val name : String, val pass : String, val stamps : Int, var stampList: ArrayList<Stamp> = arrayListOf()) {
    constructor():this("", "","",0)
    constructor(id:String, pass:String):this(id, "",pass,0)

    override fun toString(): String {
        return "[id = $id, name = $name, pass = $pass, stamps = $stamps, stampList = $stampList]"
    }
}