package com.alfian.allecgallery.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Bouquet(
    val bouquetId: Long,
    val name: String,
    val image: Int,
    val description: String,
    val bouquetPrice :Int,
    val count: Int = 0
)