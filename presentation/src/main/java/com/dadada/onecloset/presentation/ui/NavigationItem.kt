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




interface Destination {
    val route: String
    val title: String
}

object NavigationRouteName {
    const val TAB ="tab"
    const val HOME = "home"
    const val CLOSET = "closet"
}

object NavigationTitle {
    const val TAB ="탭"
    const val HOME = "빠른 실행"
    const val CLOSET = "옷장"
}