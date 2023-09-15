package com.dadada.onecloset.presentation.ui

import android.net.Uri
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
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dadada.onecloset.presentation.ui.account.MyPageScreen
import com.dadada.onecloset.presentation.ui.closet.ClothListScreen
import com.dadada.onecloset.presentation.ui.closet.ClothCourseScreen
import com.dadada.onecloset.presentation.ui.closet.ClothAnalysisScreen
import com.dadada.onecloset.presentation.ui.closet.ClothScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationResultScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationScreen
import com.dadada.onecloset.presentation.ui.fitting.FittingResultScreen
import com.dadada.onecloset.presentation.ui.fitting.FittingScreen
import com.dadada.onecloset.presentation.ui.home.MainTabScreen
import com.dadada.onecloset.presentation.ui.photo.CameraScreen
import com.dadada.onecloset.presentation.ui.photo.GalleryScreen
import com.dadada.onecloset.presentation.ui.photo.PhotoScreen

private const val TAG = "MainScreen"
@Composable
fun MainScreen() {
    val scaffoldState = rememberScrollState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            MainHeader(
                navController = navController,
                currentRoute = currentRoute,
            )
        }
    ) {
        MainNavigationScreen(
            innerPaddings = it,
            navController = navController,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(navController: NavHostController, currentRoute: String?) {
    if (currentRoute == CameraNav.route || currentRoute == GalleryNav.route) {
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
                IconButton(onClick = { navController.navigate(AccountNav.route) }) {
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
    navController: NavHostController,
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
        composable(route = ClosetDetailNav.route) {
            ClothListScreen(navHostController = navController)
        }
        composable(route = GalleryNav.route) {
            GalleryScreen(navController)
        }
        
        composable(route = "${ClothAnalysisNav.route}/{photoUri}") {
            val uriArg = it.arguments?.getString("photoUri")
            if (uriArg != null) {
                val decodedUri = Uri.decode(uriArg)
                ClothAnalysisScreen(navController, photoUri = decodedUri.toUri())
            }
        }
        composable(route = "${ClothCourseNav.route}/{photoUri}") {
            val uriArg = it.arguments?.getString("photoUri")
            if (uriArg != null) {
                val decodedUri = Uri.decode(uriArg)
                ClothCourseScreen(navController)
            }
        }
        composable(route = ClothNav.route) {
            ClothScreen(navHostController = navController)
        }
        composable(route = AccountNav.route) {
            MyPageScreen()
        }
        composable(route = FittingNav.route) {
            FittingScreen(navHostController = navController)
        }
        composable(route = CoordinationNav.route) {
            CoordinationScreen(navHostController = navController)
        }
        composable(route = FittingResultNav.route) {
            FittingResultScreen()
        }
        composable(route = PhotoNav.route) {
            PhotoScreen()
        }
        composable(route = CoordinationResultNav.route) {
            CoordinationResultScreen()
        }
    }
}
