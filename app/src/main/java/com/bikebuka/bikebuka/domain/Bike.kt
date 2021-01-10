package com.bikebuka.bikebuka.domain

import androidx.room.Entity

@Entity(tableName = "bikes")
data class Bike(
    var name: String,
    var description: String,
    var price: String
)