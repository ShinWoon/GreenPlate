package com.ssafy.green_plate.models

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import java.util.*

data class OrderDetailResponse(
    @SerializedName("o_id") val orderId: Int,
    @SerializedName("order_table") val orderTable: String,
    @SerializedName("order_time") val orderDate: Date,
    @SerializedName("completed") val orderCompleted: Char='N',
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("product_id") val productId: Int,
    @SerializedName("name") val productName: String,
    @SerializedName("unitprice") val unitPrice: Int,
    @SerializedName("img") val img: String,
    @SerializedName("stamp") val stampCount: Int,
    @SerializedName("totalprice") val totalPrice: Int,
    @SerializedName("type") val productType: String,
    @SerializedName("added_stuff") val addedStuff : String,
    @SerializedName("added_quantity") val addedQuantity : String,
    @SerializedName("discount") val discount : Int,
    @SerializedName("store_name") val storeName : String,
    @SerializedName("dressing_id") val dressingId : Int,
    @SerializedName("pay_type") val payType : String,
    @SerializedName("total_order_price") val totalOrderPrice : Int,
    var finalPrice : Int
)