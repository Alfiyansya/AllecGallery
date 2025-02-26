package com.alfian.allecgallery.ui.screens.cart

import com.alfian.allecgallery.domain.model.OrderBouquet

data class CartState(
    val orderBouquet: List<OrderBouquet>,
    val totalPrice: Int
)