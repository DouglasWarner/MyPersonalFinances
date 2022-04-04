package com.douglas.mypersonalfinances.di

import com.douglas.mypersonalfinances.data.MpfApiClient
import com.douglas.mypersonalfinances.data.MpfApiClient.URLS.URL_Base
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAPI() =
        Retrofit.Builder()
            .baseUrl(URL_Base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideTransactionApiClient(retrofit: Retrofit) = retrofit.create(MpfApiClient::class.java)
}