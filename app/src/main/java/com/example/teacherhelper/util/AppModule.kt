package com.example.teacherhelper.util

import android.content.Context
import androidx.room.Room
import com.example.teacherhelper.repository.dao.Dao
import com.example.teacherhelper.repository.data.GroupDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль для инжектирования базы данных
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    /**
     * Инжект для базы данных
     * @param context контекст приложения
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) =
        Room.databaseBuilder(context, GroupDatabase::class.java, Constants.NAME_DATABASE)
            .fallbackToDestructiveMigration()
            .build()

    /**
     * инжект для дао
     * @param appDatabase база данных
     */
    @Singleton
    @Provides
    fun provideDAO(appDatabase: GroupDatabase): Dao {
        return appDatabase.dao()
    }
}