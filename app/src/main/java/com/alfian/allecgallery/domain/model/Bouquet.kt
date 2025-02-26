package com.alfian.allecgallery.domain.model


data class Bouquet(
    val bouquetId: Long,
    val name: String,
    val image: Int,
    val description: String,
    val bouquetPrice :Int,
    val count: Int = 0
)