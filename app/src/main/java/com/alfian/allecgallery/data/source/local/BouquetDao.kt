package com.alfian.allecgallery.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BouquetDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBouquets(bouquetList: List<BouquetEntity>)

    @Query("SELECT * FROM bouquet")
    fun getAllBouquet(): Flow<List<BouquetEntity>>

    @Query("SELECT * FROM bouquet WHERE name LIKE '%' || :query || '%'")
    fun searchBouquet(query: String): Flow<List<BouquetEntity>>

    @Query("SELECT * FROM bouquet WHERE bouquetId = :bouquetId")
    fun getBouquetById(bouquetId: Long): Flow<BouquetEntity>

    @Query("SELECT * FROM bouquet WHERE count > 0")
    fun getOrderBouquet(): Flow<List<BouquetEntity>>

    @Query("UPDATE bouquet SET count = :count WHERE bouquetId = :bouquetId")
    suspend fun updateOrderBouquet(bouquetId: Long, count: Int)
}