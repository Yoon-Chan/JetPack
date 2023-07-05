package com.example.chap47_bottombardemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chap47_bottombardemo.screens.Contacts
import com.example.chap47_bottombardemo.screens.Favorites
import com.example.chap47_bottombardemo.screens.Home
import com.example.chap47_bottombardemo.ui.theme.Chap47_BottomBarDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chap47_BottomBarDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Bottom Navigation Demo")})},
        content = { NavigationHost(navController = navController)},
        bottomBar = { BottomNavigationBar(navController = navController)}
    )
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            Home()
        }

        composable(NavRoutes.Contacts.route) {
            Contacts()
        }

        composable(NavRoutes.Favorites.route) {
            Favorites()
        }
    }
}

//내비게이션 바 디자인하기
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoutes = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoutes == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = navItem.image, contentDescription = navItem.title ) },
                label = { Text(text = navItem.title)},
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chap47_BottomBarDemoTheme {
        MainScreen()
    }
}