package com.alfian.allecgallery.domain.repository

import com.alfian.allecgallery.domain.model.Bouquet
import kotlinx.coroutines.flow.Flow

interface BouquetRepository {
    suspend fun insertAllBouquets(bouquet: List<Bouquet>)
    suspend fun getAllBouquets(): Flow<List<Bouquet>>
    suspend fun searchBouquet(query: String): Flow<List<Bouquet>>
    suspend fun getOrderBouquetById(bouquetId: Long): Bouquet
    suspend fun getOrderBouquet(): Flow<List<Bouquet>>
    suspend fun updateOrderBouquet(bouquetId: Long, newCountValue: Int): Flow<Boolean>
}