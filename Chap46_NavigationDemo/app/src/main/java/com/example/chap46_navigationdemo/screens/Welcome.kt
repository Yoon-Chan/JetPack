package com.example.chap46_navigationdemo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chap46_navigationdemo.NavRoutes

@Composable
fun Welcome(navController: NavController, userName : String?){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome $userName", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = { navController.navigate(NavRoutes.Profile.route){
                //프로파일에서 뒤로가면 바로 홈 화면으로 이동하기.
                popUpTo(NavRoutes.Home.route)
            } }){
                Text(text = "Set up your profile")
            }
        }
    }
}