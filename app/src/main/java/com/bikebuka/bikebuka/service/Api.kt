package com.bikebuka.bikebuka.service

import com.bikebuka.bikebuka.service.response.Bike
import com.bikebuka.bikebuka.service.response.BikesList
import com.bikebuka.bikebuka.service.response.Booking
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("/api/controller/v1/bookings/index.php")
    fun getBikes(): Observable<BikesList>

    @GET("/api/controller/v1/bookings/show.php")
    fun filterBikeByLocation(@Query("q") location: String): Observable<BikesList>

    @GET("/api/controller/v1/bookings/read.php")
    fun getBikeById(@Query("bike_id") id: String): Observable<Bike>

    @POST("/")
    fun postBookingInfo(@Body booking: Booking)
}