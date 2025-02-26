package com.alfian.allecgallery.data.repository

//import com.alfian.allecgallery.util.Mapper.toBouquet
//import com.alfian.allecgallery.util.Mapper.toBouquetEntity
import com.alfian.allecgallery.data.source.local.BouquetDao
import com.alfian.allecgallery.data.source.local.FakeBouquetDataSource
import com.alfian.allecgallery.domain.model.Bouquet
import com.alfian.allecgallery.domain.repository.BouquetRepository
import com.alfian.allecgallery.util.Mapper
import com.alfian.allecgallery.util.Mapper.toBouquet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BouquetRepositoryImpl @Inject constructor(val bouquetDao: BouquetDao) : BouquetRepository {
    init {
        CoroutineScope(Dispatchers.IO).launch {
            bouquetDao.insertAllBouquets(Mapper.mapBouquetModelToEntities(FakeBouquetDataSource.dummyBouquets) )
        }
    }

    override suspend fun insertAllBouquets(bouquet: List<Bouquet>) {
        bouquetDao.insertAllBouquets(Mapper.mapBouquetModelToEntities(bouquet))
    }

    override suspend fun getAllBouquets(): Flow<List<Bouquet>> {
        return flowOf(Mapper.mapBouquetEntitiesToModel(bouquetDao.getAllBouquet().first()))
    }

    override suspend fun searchBouquet(query: String): Flow<List<Bouquet>> {
        return flowOf(Mapper.mapBouquetEntitiesToModel(bouquetDao.searchBouquet(query).first()))
    }

    override suspend fun getOrderBouquetById(bouquetId: Long): Bouquet {
        return bouquetDao.getBouquetById(bouquetId).first().toBouquet()
    }

    override suspend fun getOrderBouquet(): Flow<List<Bouquet>> {
        return flowOf(Mapper.mapBouquetEntitiesToModel(bouquetDao.getOrderBouquet().first()))
    }

    override suspend fun updateOrderBouquet(bouquetId: Long, newCountValue: Int): Flow<Boolean> {
        bouquetDao.updateOrderBouquet(bouquetId,newCountValue)
        return flowOf(true)
    }
}