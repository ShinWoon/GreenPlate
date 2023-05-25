package com.ssafy.green_plate.api

import com.ssafy.green_plate.dto.Store
import retrofit2.http.GET


interface StoreService {
    @GET("rest/store")
    suspend fun getStore() : List<Store>
}