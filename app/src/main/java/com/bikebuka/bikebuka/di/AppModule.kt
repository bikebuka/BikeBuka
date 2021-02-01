package com.bikebuka.bikebuka.di

import android.content.Context
import androidx.room.Room
import com.bikebuka.bikebuka.BuildConfig
import com.bikebuka.bikebuka.domain.BikeBukaDatabase
import com.bikebuka.bikebuka.domain.BikeDao
import com.bikebuka.bikebuka.repository.BikeRepository
import com.bikebuka.bikebuka.repository.BikeRepositoryImpl
import com.bikebuka.bikebuka.service.Api
import com.bikebuka.bikebuka.utils.Constants.Companion.BASEURL
import com.bikebuka.bikebuka.utils.Constants.Companion.DATABASENAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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

    @Provides
    fun provideBikeRepository(dao: BikeDao, api: Api) =
        BikeRepositoryImpl(dao, api) as BikeRepository

    @Singleton
    @Provides
    fun provideBikeBukaApi(): Api =
        Retrofit.Builder()
            .client(createClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
            .build()
            .create(Api::class.java)

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor: HttpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }
}