package com.alfian.allecgallery.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BouquetEntity::class], version = 1, exportSchema = false)
abstract class BouquetDatabase: RoomDatabase(){
    abstract fun getBouquetDao(): BouquetDao
}