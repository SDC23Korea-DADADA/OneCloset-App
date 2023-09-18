package com.dadada.onecloset.presentation.ui

import android.net.Uri
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dadada.onecloset.presentation.ui.account.LogInScreen
import com.dadada.onecloset.presentation.ui.account.MyPageScreen
import com.dadada.onecloset.presentation.ui.closet.ClothAnalysisScreen
import com.dadada.onecloset.presentation.ui.closet.ClothCourseScreen
import com.dadada.onecloset.presentation.ui.closet.ClothListScreen
import com.dadada.onecloset.presentation.ui.closet.ClothScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationResultScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationScreen
import com.dadada.onecloset.presentation.ui.fitting.FittingResultScreen
import com.dadada.onecloset.presentation.ui.fitting.FittingScreen
import com.dadada.onecloset.presentation.ui.home.MainTabScreen
import com.dadada.onecloset.presentation.ui.photo.CameraScreen
import com.dadada.onecloset.presentation.ui.photo.GalleryScreen
import com.dadada.onecloset.presentation.ui.photo.PhotoScreen
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.dadada.onecloset.presentation.ui.NavigationItem.*

private const val TAG = "MainScreen"

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(startDestination: String) {
    val scaffoldState = rememberScrollState()
    val navController = rememberAnimatedNavController()
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
            startDestination = startDestination
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(navController: NavHostController, currentRoute: String?) {
    if (NavigationItem.isNoToolbar(currentRoute)) {
        return
    }
    TopAppBar(
        modifier = Modifier.padding(top = Paddings.large),
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigationScreen(
    innerPaddings: PaddingValues,
    navController: NavHostController,
    startDestination: String
) {
    AnimatedNavHost(
        modifier = Modifier.padding(innerPaddings),
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(route = NavigationItem.LogInNav.route) {
            LogInScreen(navHostController = navController)
        }
        composable(route = MainTabNav.route) {
            MainTabScreen(navController)
        }
        composable(route = CameraNav.route) {
            CameraScreen()
        }
        composable(route = ClosetDetailNav.route) {
            val parentEntry = remember(it) { navController.getBackStackEntry(NavigationRouteName.TAB) }
            ClothListScreen(navHostController = navController, closetViewModel = hiltViewModel(parentEntry))
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
            val parentEntry = remember(it) { navController.getBackStackEntry(NavigationRouteName.TAB) }
            ClothScreen(navHostController = navController, closetViewModel = hiltViewModel(parentEntry))
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
