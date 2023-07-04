package com.example.chap46_navigationdemo

//내비게이션 경로 설정
sealed class NavRoutes(val route : String){
    object Home : NavRoutes("home")
    object Welcome : NavRoutes("welcome")
    object Profile : NavRoutes("profile")
}
