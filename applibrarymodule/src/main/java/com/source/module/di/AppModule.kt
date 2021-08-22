package com.source.module.di

import android.content.Context
import com.source.module.R
import com.source.module.custom.CardScaleHelper
import com.source.module.custom.ItemOffsetDecoration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCardScaleHelper() = CardScaleHelper()

    @Singleton
    @Provides
    @Named(OFFSET_SPACING_SMALL)
    fun provideItemOffsetDecoration(@ApplicationContext context: Context): ItemOffsetDecoration {
        val space = context.resources.getDimensionPixelOffset(R.dimen.default_spacing_2dp)
        return ItemOffsetDecoration(space)
    }


    companion object {
        const val OFFSET_SPACING_SMALL = "off_set_spacing_small"
    }

}