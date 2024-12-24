package com.example.reterofitapi

import retrofit2.Call
import retrofit2.http.GET

interface apiinterface {
    @GET("products")
    fun getproduct():Call<mydata>
}