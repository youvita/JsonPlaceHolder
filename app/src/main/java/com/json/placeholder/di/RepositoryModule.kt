package com.json.placeholder.di

import androidx.paging.PagedList
import com.json.placeholder.data.api.CommentApi
import com.json.placeholder.data.api.PassengerApi
import com.json.placeholder.data.db.AppDataBase
import com.json.placeholder.repository.comment.CommentRepo
//import com.json.placeholder.data.db.AppDataBase
//import com.json.placeholder.data.db.CommentDao
import com.json.placeholder.repository.passenger.PassengerRepo
//import com.json.placeholder.repository.comment.CommentRepo
import com.json.placeholder.repository.login.LoginRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCommentRepo(commentApi: CommentApi, dp: AppDataBase): CommentRepo {
        return CommentRepo(commentApi, dp)
    }

    @Singleton
    @Provides
    fun provideLoginRepo() : LoginRepo {
        return LoginRepo()
    }

    @Singleton
    @Provides
    fun providePassengerRepo(passengerApi: PassengerApi) : PassengerRepo {
        return PassengerRepo(passengerApi)
    }



}