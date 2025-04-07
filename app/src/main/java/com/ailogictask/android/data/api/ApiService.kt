package com.ailogictask.android.data.api

import com.ailogictask.android.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int = 12): ProductResponse
}

