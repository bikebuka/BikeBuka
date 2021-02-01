package com.bikebuka.bikebuka.domain

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Bike::class], version = 2)
abstract class BikeBukaDatabase : RoomDatabase() {
    abstract fun shoppingDao(): BikeDao
}