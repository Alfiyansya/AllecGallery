package com.alfian.allecgallery.util

import com.alfian.allecgallery.data.source.local.BouquetEntity
import com.alfian.allecgallery.domain.model.Bouquet


object Mapper {
    fun BouquetEntity.toBouquet(): Bouquet {
        return Bouquet(
            bouquetId = this.bouquetId,
            name = this.name,
            image = this.image,
            description = this.description,
            bouquetPrice = this.bouquetPrice,
            count = this.count
        )
    }

    fun mapBouquetEntitiesToModel(input: List<BouquetEntity>): List<Bouquet> = input.map {
        Bouquet(
            bouquetId = it.bouquetId,
            name = it.name,
            image = it.image,
            description = it.description,
            bouquetPrice = it.bouquetPrice,
            count = it.count
        )
    }

    fun mapBouquetModelToEntities(input: List<Bouquet>): List<BouquetEntity> = input.map {
        BouquetEntity(
            bouquetId = it.bouquetId,
            name = it.name,
            image = it.image,
            description = it.description,
            bouquetPrice = it.bouquetPrice,
            count = it.count
        )
    }
}