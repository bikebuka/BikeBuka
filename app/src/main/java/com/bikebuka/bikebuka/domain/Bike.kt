package com.bikebuka.bikebuka.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bikes")
data class Bike(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var description: String,
    var price: String
)
