package com.bikebuka.bikebuka.di

import android.content.Context
import androidx.room.Room
import com.bikebuka.bikebuka.domain.BikeBukaDatabase
import com.bikebuka.bikebuka.service.Api
import com.bikebuka.bikebuka.utils.Constants.Companion.BASEURL
import com.bikebuka.bikebuka.utils.Constants.Companion.DATABASENAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideShoppingItemDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BikeBukaDatabase::class.java, DATABASENAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(database: BikeBukaDatabase) =
        database.shoppingDao()

    @Singleton
    @Provides
    fun provideBikeBukaApi(): Api =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
            .build()
            .create(Api::class.java)
}