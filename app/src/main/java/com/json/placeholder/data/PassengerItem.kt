package com.json.placeholder.data


import com.google.gson.annotations.SerializedName

data class PassengerItem(

    @SerializedName("airline")
    val passengerAirline: List<PassengerAirline>? = null,

    @SerializedName("_id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("trips")
    val trips: Int? = null,
)