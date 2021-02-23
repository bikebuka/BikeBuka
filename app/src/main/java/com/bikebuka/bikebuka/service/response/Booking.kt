package com.bikebuka.bikebuka.service.response


import com.google.gson.annotations.SerializedName

data class Booking(
    @SerializedName("bike_id")
    val bikeId: Int,
    @SerializedName("driver_name")
    val driverName: String,
    @SerializedName("drop__off_date_time")
    val dropOffDateTime: String,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("pick_up_address")
    val pickUpAddress: String,
    @SerializedName("pick_up_date_time")
    val pickUpDateTime: String,
    @SerializedName("ride_duration")
    val rideDuration: Int,
    @SerializedName("total_amount")
    val totalAmount: Int
)