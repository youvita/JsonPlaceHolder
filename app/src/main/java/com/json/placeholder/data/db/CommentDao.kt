package com.json.placeholder.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.json.placeholder.data.CommentsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comment: List<CommentsItem>)

    @Query("SELECT * FROM tbComment")
    fun getAllComments(): Flow<List<CommentsItem>>

    @Query("DELETE FROM tbComment")
    suspend fun deleteAllComments()
}