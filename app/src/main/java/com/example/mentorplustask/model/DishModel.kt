package com.example.mentorplustask.model

import java.io.Serializable

data class DishModel(
    val dish_name: String,
    val customisation : ArrayList<String>,
    val price :  Int
):Serializable