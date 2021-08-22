package com.json.placeholder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.json.placeholder.data.CommentsItem

@Database(entities = [CommentsItem::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun commentDao(): CommentDao

}