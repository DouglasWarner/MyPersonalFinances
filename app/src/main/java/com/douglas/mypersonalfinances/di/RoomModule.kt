package com.douglas.mypersonalfinances.di

import android.content.Context
import androidx.room.Room
import com.douglas.mypersonalfinances.data.MpfDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MpfDatabase::class.java, MpfDatabase.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMyTransactionDAO(db: MpfDatabase) = db.MyTransactionDAO()

    @Singleton
    @Provides
    fun provideMyPlannedDAO(db: MpfDatabase) = db.MyPlannedDAO()

    @Singleton
    @Provides
    fun provideUserAppDAO(db: MpfDatabase) = db.UserAppDAO()

    @Singleton
    @Provides
    fun provideCategoryDAO(db: MpfDatabase) = db.CategoryDAO()

}