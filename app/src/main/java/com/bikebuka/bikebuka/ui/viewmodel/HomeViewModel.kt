package com.bikebuka.bikebuka.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import com.bikebuka.bikebuka.di.BaseViewModel
import com.bikebuka.bikebuka.repository.BikeRepository
import com.bikebuka.bikebuka.service.response.Bike
import com.bikebuka.bikebuka.service.response.BikesList
import com.bikebuka.bikebuka.service.response.Booking
import io.reactivex.Observable

class HomeViewModel @ViewModelInject constructor(
    private val bikeRepository: BikeRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    fun getBikes(): Observable<BikesList> {
        return bikeRepository.getBikes()
    }

    fun filterBikeByLocation(location: String): Observable<BikesList> {
        return bikeRepository.filterBikeByLocation(location)
    }

    fun getBikeById(id: String): Observable<Bike> {
        return bikeRepository.getBikeById(id)
    }

    fun saveBooking(booking: Booking) {
        return bikeRepository.saveBooking(booking)
    }
}