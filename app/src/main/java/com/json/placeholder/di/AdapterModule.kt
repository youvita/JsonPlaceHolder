package com.json.placeholder.di

import com.json.placeholder.ui.adapter.CommentAdapter
import com.source.module.app.AppExecutors
import com.source.module.custom.CardScaleHelper
import com.source.module.custom.ItemOffsetDecoration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Singleton
    @Provides
    fun provideCommentAdapter(appExecutors: AppExecutors) = CommentAdapter(appExecutors)

}