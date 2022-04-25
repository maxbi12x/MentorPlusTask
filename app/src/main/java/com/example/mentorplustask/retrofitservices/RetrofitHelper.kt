package com.example.mentorplustask.retrofitservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    const val BASE_URL ="https://demo3755793.mockable.io/"
//    const val BASE_URL = "https://run.mocky.io/v3/"
    fun getInstance() : Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}