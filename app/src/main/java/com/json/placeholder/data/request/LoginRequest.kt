package com.json.placeholder.data.request


import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("email")
    var email: String? = "1",

    @SerializedName("password")
    var password: String? = "1"

)