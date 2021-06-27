package com.bikebuka.bikebuka.repository

import com.bikebuka.bikebuka.service.response.Bike
import com.bikebuka.bikebuka.service.response.BikesList
import com.bikebuka.bikebuka.service.response.Booking
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call

interface BikeRepository {
    fun getBikes(): Observable<BikesList>

    fun filterBikeByLocation(location: String): Observable<BikesList>
    fun getBikeById(id: String): Observable<Bike>

    fun saveBooking(booking: Booking)
}