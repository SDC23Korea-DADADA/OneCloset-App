package com.dadada.onecloset.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.dadada.onecloset.presentation.R

sealed class NavigationItem(override val route: String, override val title: String) : Destination {
    object LogInNav : Destination {
        override val route: String = NavigationRouteName.LOGIN
        override val title: String = NavigationTitle.LOGIN
    }

    object MainTabNav : Destination {
        override val route: String = NavigationRouteName.TAB
        override val title: String = NavigationTitle.TAB
    }

    object HomeNav : Destination {
        override val route: String = NavigationRouteName.HOME
        override val title: String = NavigationTitle.HOME
    }

    object ClosetNav : Destination {
        override val route: String = NavigationRouteName.CLOSET
        override val title: String = NavigationTitle.CLOSET
    }

    object CameraNav : Destination {
        override val route: String = NavigationRouteName.CAMERA
        override val title: String = NavigationTitle.CAMERA
    }

    object ClosetDetailNav : Destination {
        override val route: String = NavigationRouteName.CLOSET_DETAIL
        override val title: String = NavigationTitle.CLOSET_DETAIL
    }

    object ClothAnalysisNav : Destination {
        override val route: String = NavigationRouteName.CLOTH_ANALYSIS
        override val title: String = NavigationTitle.CLOTH_ANALYSIS
    }

    object ClothCourseNav : Destination {
        override val route: String = NavigationRouteName.CLOTH_COURSE
        override val title: String = NavigationTitle.CLOTH_COURSE
    }

    object ClothNav : Destination {
        override val route: String = NavigationRouteName.CLOTH
        override val title: String = NavigationTitle.CLOTH
    }

    object FittingNav : Destination {
        override val route: String = NavigationRouteName.FITTING
        override val title: String = NavigationTitle.FITTING
    }

    object CoordinationNav : Destination {
        override val route: String = NavigationRouteName.COORDINATION
        override val title: String = NavigationTitle.COORDINATION
    }

    object CoordinationRegisterNav : Destination {
        override val route: String = NavigationRouteName.COORDINATION_REGISTER
        override val title: String = NavigationTitle.COORDINATION_REGISTER
    }

    object CoordinationResultNav : Destination {
        override val route: String = NavigationRouteName.COORDINATION_RESULT
        override val title: String = NavigationTitle.COORDINATION_RESULT
    }

    object CoordinationDetailNav : Destination {
        override val route: String = NavigationRouteName.COORDINATION_DETAIL
        override val title: String = NavigationTitle.COORDINATION_DETAIL
    }

    object CoordinationFittingDetailNav : Destination {
        override val route: String = NavigationRouteName.COORDINATION_FITTING_DETAIL
        override val title: String = NavigationTitle.COORDINATION_FITTING_DETAIL
    }


    object GalleryNav : Destination {
        override val route: String = NavigationRouteName.GALLERY
        override val title: String = NavigationTitle.GALLERY
    }

    object AccountNav : Destination {
        override val route: String = NavigationRouteName.ACCOUNT
        override val title: String = NavigationTitle.ACCOUNT
    }

    object PhotoNav : Destination {
        override val route: String = NavigationRouteName.PHOTO
        override val title: String = NavigationTitle.PHOTO
    }

    object FittingResultNav : Destination {
        override val route: String = NavigationRouteName.FITTING_RESULT
        override val title: String = NavigationTitle.FITTING_RESULT
    }

    companion object {
        fun isNoToolbar(route: String?): Boolean {
            return when (route) {
                CoordinationFittingDetailNav.route, CoordinationDetailNav.route, CameraNav.route, GalleryNav.route, LogInNav.route, "${ClothNav.route}/{clothId}" -> true

                else -> false
            }
        }

        fun getToolbarIcon(route: String?): ImageVector? {
            return when (route) {
                ClothNav.route -> Icons.Default.MoreVert
                MainTabNav.route -> Icons.Default.AccountCircle
                else -> null
            }
        }

        fun getNavigationTitleByRoute(targetRoute: String?): String? {
            return when (targetRoute) {
                LogInNav.route -> LogInNav.title
                MainTabNav.route -> MainTabNav.title
                HomeNav.route -> HomeNav.title
                ClosetNav.route -> ClosetNav.title
                CameraNav.route -> CameraNav.title
                ClosetDetailNav.route -> ClosetDetailNav.title
                ClothAnalysisNav.route -> ClothAnalysisNav.title
                ClothCourseNav.route -> ClothCourseNav.title
                ClothNav.route -> ClothNav.title
                FittingNav.route -> FittingNav.title
                CoordinationNav.route -> CoordinationNav.title
                CoordinationRegisterNav.route -> CoordinationRegisterNav.title
                CoordinationResultNav.route -> CoordinationResultNav.title
                CoordinationDetailNav.route -> CoordinationDetailNav.title
                CoordinationFittingDetailNav.route -> CoordinationFittingDetailNav.title
                GalleryNav.route -> GalleryNav.title
                AccountNav.route -> AccountNav.title
                PhotoNav.route -> PhotoNav.title
                FittingResultNav.route -> FittingResultNav.title
                else -> null
            }
        }
    }

}

interface Destination {
    val route: String
    val title: String
}

object NavigationRouteName {
    const val LOGIN = "login"
    const val TAB = "tab"
    const val HOME = "home"
    const val CLOSET = "closet"
    const val CAMERA = "camera"
    const val CLOSET_DETAIL = "closet_detail"
    const val CLOTH_ANALYSIS = "cloth_analysis"
    const val CLOTH_COURSE = "cloth_course"
    const val GALLERY = "gallery"
    const val CLOTH = "cloth"
    const val ACCOUNT = "account"
    const val FITTING = "fitting"
    const val COORDINATION = "coordination"
    const val COORDINATION_REGISTER = "coordination_register"
    const val COORDINATION_RESULT = "coordination_result"
    const val COORDINATION_DETAIL = "coordination_detail"
    const val COORDINATION_FITTING_DETAIL = "coordination_fitting_detail"
    const val FITTING_RESULT = "fitting_result"
    const val PHOTO = "photo"
}

object NavigationTitle {
    const val LOGIN = "로그인"
    const val TAB = "탭"
    const val HOME = "빠른 실행"
    const val CLOSET = "옷장"
    const val CAMERA = "카메라"
    const val CLOSET_DETAIL = "옷장 상세"
    const val CLOTH_ANALYSIS = "의류 등록"
    const val CLOTH_COURSE = "코스 추천"
    const val GALLERY = "갤러리"
    const val CLOTH = "옷 상세"
    const val ACCOUNT = "계정"
    const val FITTING = "가상피팅"
    const val COORDINATION = "데일리코디"
    const val COORDINATION_REGISTER = "코디 등록"
    const val COORDINATION_RESULT = "coordination"
    const val COORDINATION_DETAIL = "코디 상세"
    const val COORDINATION_FITTING_DETAIL = "계획 상세"
    const val FITTING_RESULT = "가상피팅 결과"
    const val PHOTO = "사진"
}