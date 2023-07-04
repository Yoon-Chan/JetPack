package com.example.chap47_bottombardemo

sealed class NavRoutes(val route : String){
    object Home : NavRoutes("Home")
    object Contacts : NavRoutes("contacts")
    object Favorites : NavRoutes("favorites")
}
