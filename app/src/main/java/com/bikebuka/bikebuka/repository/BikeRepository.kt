package com.bikebuka.bikebuka.repository

import com.bikebuka.bikebuka.service.response.BikesList
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call

interface BikeRepository {
    fun getBikes(): Observable<BikesList>
}