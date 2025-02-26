package com.alfian.allecgallery.di

import android.content.Context
import androidx.room.Room
import com.alfian.allecgallery.data.source.local.BouquetDao
import com.alfian.allecgallery.data.source.local.BouquetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BouquetDatabase {
        return Room.databaseBuilder(
            context,
            BouquetDatabase::class.java,
            "bouquet.db"
        )
            .fallbackToDestructiveMigrationFrom()
            .build()
    }

    @Provides
    fun provideBouquetDao(database: BouquetDatabase): BouquetDao = database.getBouquetDao()
}