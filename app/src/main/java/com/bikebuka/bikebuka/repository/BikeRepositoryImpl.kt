package com.bikebuka.bikebuka.repository

import com.bikebuka.bikebuka.domain.BikeDao
import com.bikebuka.bikebuka.network.Api
import javax.inject.Inject

class BikeRepositoryImpl @Inject constructor(
    val bikeDao: BikeDao,
    val api: Api
) : BikeRepository {

}