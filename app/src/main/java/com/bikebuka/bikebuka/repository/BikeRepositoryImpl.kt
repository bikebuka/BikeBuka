package com.bikebuka.bikebuka.repository

import com.bikebuka.bikebuka.domain.BikeDao
import com.bikebuka.bikebuka.service.Api
import com.bikebuka.bikebuka.service.response.BikesList
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class BikeRepositoryImpl @Inject constructor(
    private val bikeDao: BikeDao,
    private val api: Api
) : BikeRepository {
    override fun getBikes(): Observable<BikesList> {
        return api.getBikes()
    }

    override fun filterBikeByLocation(location: String): Observable<BikesList> {
        return api.filterBikeByLocation(location)
    }

}