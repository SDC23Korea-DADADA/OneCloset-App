package com.dadada.onecloset.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dadada.onecloset.presentation.ui.course.CameraScreen
import com.dadada.onecloset.presentation.ui.home.MainTabScreen

@Composable
fun MainScreen() {
    val scaffoldState = rememberScrollState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { MainHeader(navController = navController, currentRoute = currentRoute) }
    ) {
        MainNavigationScreen(innerPaddings = it, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(navController: NavHostController, currentRoute: String?) {
    if(currentRoute == CameraNav.route) {
        return
    }
    TopAppBar(
        modifier = Modifier.padding(top = 12.dp),
        title = { Text("One Closet", fontWeight = FontWeight.ExtraBold) },
        navigationIcon = {
            if (currentRoute != MainTabNav.route) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                }
            } else {
                null
            }
        },
        actions = {
            if (currentRoute == MainTabNav.route) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "account")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun MainNavigationScreen(
    innerPaddings: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(innerPaddings),
        navController = navController,
        startDestination = MainTabNav.route
    ) {
        composable(route = MainTabNav.route) {
            MainTabScreen(navController)
        }
        composable(route = CameraNav.route) {
            CameraScreen()
        }
    }
}
