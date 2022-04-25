package com.example.mentorplustask.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentorplustask.network.Network
import com.example.mentorplustask.model.DishModel
import com.example.mentorplustask.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repositoryElement: Repository,context : Context): ViewModel() {

    init{
        viewModelScope.launch(Dispatchers.IO) {
            if(Network.isConnected(context)){
                repositoryElement.getDishes()
            }
            else{
                Log.e("INTERNET","NOT Connected to Netwotk")
            }

        }
    }
    val dishes: LiveData<ArrayList<DishModel>>
    get() = repositoryElement.dishes
}