package com.bikebuka.bikebuka.service

import com.bikebuka.bikebuka.service.response.BikesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api/controller/v1/bookings/index.php")
    fun getBikes(): Observable<BikesList>

    @GET("")
    fun filterBikeByLocation(@Query("q") location: String): Observable<BikesList>
}