package com.rahulverlekar.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.rahulverlekar.data.db.AppDatabase
import com.rahulverlekar.data.db.GhostSightingDao
import com.rahulverlekar.data.repository.GhostSightingRoomRepository
import com.rahulverlekar.domain.repository.GhostSightingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): GhostSightingDao {
        return appDatabase.ghostSightingDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGhostSightingRepository(
        repoImpl: GhostSightingRoomRepository
    ): GhostSightingRepository
}
