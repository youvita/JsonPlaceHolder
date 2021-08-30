package com.json.placeholder.di

import android.app.Application
import androidx.room.Room
import com.json.placeholder.app.Constants
import com.json.placeholder.data.api.CommentApi
import com.json.placeholder.data.api.PassengerApi
import com.json.placeholder.data.db.AppDataBase
//import com.json.placeholder.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCommentService(retrofit: Retrofit.Builder): CommentApi {
        return retrofit.baseUrl(Constants.BASE_URL).build().create(CommentApi::class.java)
    }

    @Singleton
    @Provides
    fun providePassengerService(retrofit: Retrofit.Builder): PassengerApi {
        return retrofit.baseUrl(Constants.BASE_URL).build().create(PassengerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDataBase(app: Application): AppDataBase = Room.databaseBuilder(app, AppDataBase::class.java, "app_database").build()

}