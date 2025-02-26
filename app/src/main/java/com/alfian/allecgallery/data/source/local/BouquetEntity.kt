package com.alfian.allecgallery.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bouquet")
data class BouquetEntity(
    @PrimaryKey(autoGenerate = true)
    val bouquetId: Long,
    val name: String,
    val image: Int,
    val description: String,
    val bouquetPrice: Int,
    val count: Int,
)