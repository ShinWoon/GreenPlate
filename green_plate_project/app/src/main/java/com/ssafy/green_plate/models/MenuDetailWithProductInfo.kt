package com.ssafy.green_plate.models
import com.google.gson.annotations.SerializedName

data class MenuDetailWithProductInfo (
    @SerializedName("img") val productImg: String,
    @SerializedName("quantity") val productQuantity : Int,
    @SerializedName("user_id") val userId: String,
    @SerializedName("added_stuff") val addedStuff : String,
    @SerializedName("price") val price : Int,
    @SerializedName("dressing_id") val dressingId : Int,
    @SerializedName("o_id") val orderId: Int,
    @SerializedName("name") val productName : String,
    @SerializedName("order_time") val orderTime : String,
    @SerializedName("type") val productType : String,
    @SerializedName("added_quantity") val addedQuantity : String
        )