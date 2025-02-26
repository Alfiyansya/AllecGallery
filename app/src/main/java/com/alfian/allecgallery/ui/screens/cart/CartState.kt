package com.alfian.allecgallery.ui.screens.cart

import com.alfian.allecgallery.domain.model.Bouquet

data class CartState(
    val orderBouquet: List<Bouquet>,
    val totalPrice: Int
)