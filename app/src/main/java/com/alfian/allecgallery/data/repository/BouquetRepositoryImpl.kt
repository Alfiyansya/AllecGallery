package com.alfian.allecgallery.data.repository

import com.alfian.allecgallery.data.source.local.FakeBouquetDataSource
import com.alfian.allecgallery.domain.model.OrderBouquet
import com.alfian.allecgallery.domain.repository.BouquetRepository
//import com.alfian.allecgallery.util.Mapper.toBouquet
//import com.alfian.allecgallery.util.Mapper.toBouquetEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BouquetRepositoryImpl @Inject constructor() : BouquetRepository {
    private val orderBouquets = mutableListOf<OrderBouquet>()

    init {
        if (orderBouquets.isEmpty()) {
            FakeBouquetDataSource.dummyBouquets.forEach {
                orderBouquets.add(OrderBouquet(it, 0))
            }
        }
    }

    override fun getAllBouquets(): Flow<List<OrderBouquet>> {
        return flowOf(orderBouquets)

    }

    override fun searchBouquet(query: String): Flow<List<OrderBouquet>> {
        return flowOf(orderBouquets.filter {
            it.bouquet.name.contains(query, ignoreCase = true)
        })
    }

    override fun getOrderBouquetById(bouquetId: Long): OrderBouquet {
        return orderBouquets.first {
            it.bouquet.bouquetId == bouquetId
        }
    }

    override fun getOrderBouquet(): Flow<List<OrderBouquet>> {
        return getAllBouquets()
            .map { orderBouquets ->
                orderBouquets.filter { orderBouquet ->
                    orderBouquet.count != 0
                }
            }
    }

    override fun updateOrderBouquet(bouquetId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderBouquets.indexOfFirst { it.bouquet.bouquetId == bouquetId }
        val result = if (index >= 0) {
            val orderBouquet = orderBouquets[index]
            orderBouquets[index] =
                orderBouquet.copy(bouquet = orderBouquet.bouquet, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }
}