package com.alfian.allecgallery.di

import com.alfian.allecgallery.data.repository.BouquetRepositoryImpl
import com.alfian.allecgallery.domain.repository.BouquetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBouquetRepository():BouquetRepository{
        return BouquetRepositoryImpl()
    }
}