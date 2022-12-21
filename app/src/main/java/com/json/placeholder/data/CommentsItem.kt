package com.json.placeholder.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbComment")
@Parcelize
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
): Parcelable
