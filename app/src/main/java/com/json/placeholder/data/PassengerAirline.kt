package com.json.placeholder.data


import com.google.gson.annotations.SerializedName

data class PassengerAirline(

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("established")
    val established: String? = null,

    @SerializedName("head_quaters")
    val headQuaters: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("logo")
    val logo: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("slogan")
    val slogan: String? = null,

    @SerializedName("website")
    val website: String? = null
)