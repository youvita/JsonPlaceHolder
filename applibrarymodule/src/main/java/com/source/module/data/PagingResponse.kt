package com.source.module.data

import com.google.gson.annotations.SerializedName

open class PagingResponse<T> {

    @SerializedName("totalPassengers")
    var passengers: Int? = null

    @SerializedName("totalPages")
    var pages: Int? = null

    @SerializedName("data")
    var datas: MutableList<T>? = null
}
