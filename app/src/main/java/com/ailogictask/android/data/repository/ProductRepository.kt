package com.ailogictask.android.data.repository

import com.ailogictask.android.data.api.ApiService
import com.ailogictask.android.data.model.ProductResponse
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getProducts(): ProductResponse = apiService.getProducts()
}
