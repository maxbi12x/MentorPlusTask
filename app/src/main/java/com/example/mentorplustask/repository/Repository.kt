package com.example.mentorplustask.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mentorplustask.retrofitservices.ApiService
import com.example.mentorplustask.model.DishModel
import com.example.mentorplustask.network.Network

class Repository(private val service : ApiService, private val context : Context) {
    private val dishesMutableData =MutableLiveData<ArrayList<DishModel>>()
    val dishes : LiveData<ArrayList<DishModel>>
    get() =dishesMutableData

    suspend fun getDishes(){
        if(Network.isConnected(context)){
            val result = service.getService()

            if(result?.body()!=null){
                dishesMutableData.postValue(result.body())
            }
        }
    }
}