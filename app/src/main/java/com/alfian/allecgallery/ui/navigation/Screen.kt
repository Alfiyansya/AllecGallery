package com.alfian.allecgallery.ui.navigation

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object Cart: Screen("cart")
    data object Profile: Screen("profile")
    data object DetailBouquet: Screen("home/{bouquetId}"){
        fun createRoute(bouquetId: Long) = "home/$bouquetId"
    }
}