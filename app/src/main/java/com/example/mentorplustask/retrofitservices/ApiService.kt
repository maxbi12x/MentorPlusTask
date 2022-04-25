package com.example.mentorplustask.retrofitservices


import com.example.mentorplustask.model.DishModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("dish")
//    @GET("778f5856-e6e3-40cf-b2df-b7312ba1b45d")
    suspend fun getService(): Response<ArrayList<DishModel>>
}