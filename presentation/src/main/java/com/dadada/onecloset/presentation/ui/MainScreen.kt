package com.dadada.onecloset.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dadada.onecloset.presentation.ui.home.MainTabScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scaffoldState = rememberScrollState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(

    ) {
        MainNavigationScreen(innerPaddings = it, navController = navController)
    }
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
            MainTabScreen()
        }
    }
}
