package com.bikebuka.bikebuka.service

import com.bikebuka.bikebuka.service.response.BikesList
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("/api/controller/rest-apis/bookings/index.php")
    fun getBikes(): Observable<BikesList>
}