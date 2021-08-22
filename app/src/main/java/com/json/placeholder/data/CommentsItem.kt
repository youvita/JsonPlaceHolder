package com.json.placeholder.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbComment")
data class CommentsItem(

    @SerializedName("postId")
    var postId: Int? = null,

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("body")
    var body: String? = null
)
