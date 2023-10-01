package com.dadada.onecloset.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem.*
import com.dadada.onecloset.presentation.ui.account.LogInScreen
import com.dadada.onecloset.presentation.ui.account.MyPageScreen
import com.dadada.onecloset.presentation.ui.clothes.ClothesAnalysisScreen
import com.dadada.onecloset.presentation.ui.clothes.ClothCourseScreen
import com.dadada.onecloset.presentation.ui.closet.ClothListScreen
import com.dadada.onecloset.presentation.ui.clothes.ClothScreen
import com.dadada.onecloset.presentation.ui.components.GalaxyLoadingView
import com.dadada.onecloset.presentation.ui.components.LoadingView
import com.dadada.onecloset.presentation.ui.coordination.CoordinationDetailScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationFittingDetailScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationRegisterScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationResultScreen
import com.dadada.onecloset.presentation.ui.coordination.CoordinationScreen
import com.dadada.onecloset.presentation.ui.fitting.FittingResultScreen
import com.dadada.onecloset.presentation.ui.fitting.FittingScreen
import com.dadada.onecloset.presentation.ui.photo.CameraScreen
import com.dadada.onecloset.presentation.ui.photo.GalleryScreen
import com.dadada.onecloset.presentation.ui.photo.PhotoScreen
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.LoadingType
import com.dadada.onecloset.presentation.ui.utils.Mode
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

private const val TAG = "MainScreen"

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(startDestination: String) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }
    val mainViewModel: MainViewModel = hiltViewModel()

    val loadingState by mainViewModel.loadingState.collectAsState()
    if (loadingState) {
        when (mainViewModel.loadingType) {
            LoadingType.FITTING -> {
                LoadingView(
                    animation = R.raw.animation_loading_fitting,
                    text = "가상 피팅 결과에 최대 1분이 소요돼요!",
                )
            }

            LoadingType.VALIDATION -> {
                LoadingView(
                    animation = R.raw.animation_loading_validation,
                    text = "의류 이미지가 맞나요? 확인 중이에요..",
                )
            }

            LoadingType.ANALYSIS -> {
                LoadingView(animation = R.raw.animation_loading_analysis, text = "의류를 분석 중이에요!")
            }

            else -> {
                GalaxyLoadingView()
            }
        }
    }

    Scaffold(
        topBar = {
            MainHeader(
                navController = navController,
                currentRoute = currentRoute,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        MainNavigationScreen(
            innerPaddings = it,
            navController = navController,
            startDestination = startDestination,
            mainViewModel = mainViewModel,
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
        title = { Text("One Closet", style = Typography.displayMedium) },
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
                    Icon(Icons.Filled.AccountCircle, contentDescription = "")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigationScreen(
    innerPaddings: PaddingValues,
    navController: NavHostController,
    startDestination: String,
    mainViewModel: MainViewModel,
) {
    val closetViewModel: ClosetViewModel = hiltViewModel()
    val photoViewModel: PhotoViewModel = hiltViewModel()
    val fittingViewModel: FittingViewModel = hiltViewModel()
    val codiViewModel: CodiViewModel = hiltViewModel()
    AnimatedNavHost(
        modifier = Modifier.padding(innerPaddings),
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable(route = NavigationItem.LogInNav.route) {
            LogInScreen(navHostController = navController, mainViewModel = mainViewModel)
        }
        composable(route = MainTabNav.route) {
            photoViewModel.curMode = Mode.clothes
            photoViewModel.setCheckedIndex(-1)
            MainTabScreen(navController, mainViewModel, fittingViewModel)
        }
        composable(route = CameraNav.route) {
            CameraScreen(
                navController,
                mainViewModel = mainViewModel,
                closetViewModel = closetViewModel,
                photoViewModel = photoViewModel,
                fittingViewModel = fittingViewModel,
                codiViewModel = codiViewModel,
            )
        }
        composable(route = ClosetDetailNav.route) {
            photoViewModel.curMode = Mode.clothes
            val parentEntry =
                remember(it) { navController.getBackStackEntry(NavigationRouteName.TAB) }
            ClothListScreen(
                navHostController = navController,
                mainViewModel = mainViewModel,
                closetViewModel = hiltViewModel(parentEntry),
            )
        }
        composable(route = GalleryNav.route) {
            GalleryScreen(
                navController,
                mainViewModel = mainViewModel,
                closetViewModel = closetViewModel,
                photoViewModel = photoViewModel,
                fittingViewModel = fittingViewModel,
                codiViewModel = codiViewModel,
            )
        }

        composable(route = ClothAnalysisNav.route) {
            ClothesAnalysisScreen(
                navController,
                mainViewModel = mainViewModel,
                closetViewModel = closetViewModel,
            )
        }
        composable(route = ClothCourseNav.route) {
            ClothCourseScreen(
                navController,
                mainViewModel = mainViewModel,
                closetViewModel = closetViewModel,
            )
        }
        composable(route = "${ClothNav.route}/{clothId}") {
            val clothId = it.arguments?.getString("clothId")
            if (clothId != null) {
                ClothScreen(
                    navHostController = navController,
                    mainViewModel = mainViewModel,
                    clothId = clothId,
                )
            }
        }
        composable(route = AccountNav.route) {
            MyPageScreen(
                navHostController = navController,
                mainViewModel = mainViewModel,
                photoViewModel = photoViewModel,
            )
        }
        composable(route = FittingNav.route) {
            FittingScreen(
                navHostController = navController,
                mainViewModel = mainViewModel,
                fittingViewModel = fittingViewModel,
            )
        }
        composable(route = CoordinationNav.route) {
            CoordinationScreen(
                navHostController = navController,
                mainViewModel = mainViewModel,
                photoViewModel = photoViewModel,
                codiViewModel = codiViewModel,
                fittingViewModel = fittingViewModel,
            )
        }
        composable(route = FittingResultNav.route) {
            FittingResultScreen(navController, mainViewModel, fittingViewModel)
        }
        composable(route = "${PhotoNav.route}/{imagePath}") {
            val imagePath = it.arguments?.getString("imagePath")
            if (imagePath != null) {
                PhotoScreen(navController, imagePath)
            }
        }
        composable(route = CoordinationResultNav.route) {
            CoordinationResultScreen(codiViewModel, navController)
        }
        composable(route = CoordinationRegisterNav.route) {
            CoordinationRegisterScreen(
                navHostController = navController,
                mainViewModel = mainViewModel,
                codiViewModel = codiViewModel,
            )
        }
        composable(route = CoordinationDetailNav.route) {
            CoordinationDetailScreen(
                codiViewModel = codiViewModel,
                mainViewModel = mainViewModel,
                navController = navController,
            )
        }
        composable(route = CoordinationFittingDetailNav.route) {
            CoordinationFittingDetailScreen(
                codiViewModel = codiViewModel,
                mainViewModel = mainViewModel,
                navController = navController,
            )
        }
    }
}
