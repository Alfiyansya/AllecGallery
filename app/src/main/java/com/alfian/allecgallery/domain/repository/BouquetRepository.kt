package com.alfian.allecgallery.domain.repository

import com.alfian.allecgallery.domain.model.OrderBouquet
import kotlinx.coroutines.flow.Flow

interface BouquetRepository {
    fun getAllBouquets(): Flow<List<OrderBouquet>>
    fun searchBouquet(query: String): Flow<List<OrderBouquet>>
    fun getOrderBouquetById(bouquetId: Long): OrderBouquet
    fun getOrderBouquet(): Flow<List<OrderBouquet>>
    fun updateOrderBouquet(bouquetId: Long, newCountValue: Int): Flow<Boolean>
}