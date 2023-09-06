package com.dadada.onecloset.presentation.ui


object MainTabNav: Destination {
    override val route: String = NavigationRouteName.TAB
    override val title: String = NavigationTitle.TAB
}
object HomeNav : Destination {
    override val route: String = NavigationRouteName.HOME
    override val title: String = NavigationTitle.HOME
}

object ClosetNav: Destination {
    override val route: String = NavigationRouteName.CLOSET
    override val title: String = NavigationTitle.CLOSET
}

object CameraNav: Destination {
    override val route: String = NavigationRouteName.CAMERA
    override val title: String = NavigationTitle.CAMERA
}

object ClosetDetailNav: Destination {
    override val route: String = NavigationRouteName.CLOSET_DETAIL
    override val title: String = NavigationTitle.CLOSET_DETAIL
}

object ClothCreateNav: Destination {
    override val route: String = NavigationRouteName.CLOTH_CREATE
    override val title: String = NavigationTitle.CLOTH_CREATE
}

object GalleryNav: Destination {
    override val route: String = NavigationRouteName.CLOTH_CREATE
    override val title: String = NavigationTitle.CLOTH_CREATE
}

interface Destination {
    val route: String
    val title: String
}

object NavigationRouteName {
    const val TAB ="tab"
    const val HOME = "home"
    const val CLOSET = "closet"
    const val CAMERA = "camera"
    const val CLOSET_DETAIL = "closet_detail"
    const val CLOTH_CREATE = "cloth_create"
    const val GALLERY = "gallery"
}

object NavigationTitle {
    const val TAB ="탭"
    const val HOME = "빠른 실행"
    const val CLOSET = "옷장"
    const val CAMERA = "카메라"
    const val CLOSET_DETAIL = "옷장 상세"
    const val CLOTH_CREATE = "의류 등록"
    const val GALLERY = "갤러리"
}