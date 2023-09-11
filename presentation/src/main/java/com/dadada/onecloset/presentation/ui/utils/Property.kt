package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.ui.theme.BeigeColor
import com.dadada.onecloset.presentation.ui.theme.BrownColor
import com.dadada.onecloset.presentation.ui.theme.GoldColor
import com.dadada.onecloset.presentation.ui.theme.KhakiColor
import com.dadada.onecloset.presentation.ui.theme.LavenderColor
import com.dadada.onecloset.presentation.ui.theme.MintColor
import com.dadada.onecloset.presentation.ui.theme.NavyColor
import com.dadada.onecloset.presentation.ui.theme.PinkColor
import com.dadada.onecloset.presentation.ui.theme.PurpleColor
import com.dadada.onecloset.presentation.ui.theme.SilverColor
import com.dadada.onecloset.presentation.ui.theme.SkyBlueColor
import com.dadada.onecloset.presentation.ui.theme.WineColor

sealed class Type(val id: Int, val category: String, val name: String) {
    object Top: Type(11, "상의", "탑")
    object Blouse: Type(12, "상의", "블라우스")
    object Knit: Type(13, "상의", "니트")
    object Shirt: Type(14, "상의", "셔츠")
    object HoodT: Type(15, "상의", "후드티")

    object Jean: Type(21, "하의", "청바지")
    object Pants: Type(22, "하의", "팬츠")
    object Skirt: Type(23, "하의", "치마")
    object JoggerPants: Type(24, "하의", "조거팬츠")
    object Leggings: Type(25, "하의", "레깅스")

    object Coat: Type(31, "아우터", "코트")
    object Jacket: Type(32, "아우터", "재킷")
    object Jumper: Type(33, "아우터", "점퍼")
    object Padding: Type(34, "아우터", "패딩")
    object ZipUp: Type(35, "아우터", "집업")
    object Cardigan: Type(36, "아우터", "가디건")
    object Vest: Type(37, "아우터", "조끼")

    object Dress: Type(41, "한벌옷", "드레스")
    object JumpSuit: Type(42, "한벌옷", "점프슈트")


    companion object {
        fun getAllTypes(): List<Type> {
            return Type::class.sealedSubclasses.mapNotNull { it.objectInstance as? Type }
        }

        fun getTypesByCategory(category: String): List<Type> {
            val allTypes = getAllTypes()
            return allTypes.filter { it.category == category }
        }
    }
}

sealed class Material(val id: Int, val name: String) {
    object Leader: Material(1, "가죽/무스탕")
    object Knit: Material(2, "니트")
    object Denim: Material(3, "데님")
    object Lace: Material(4, "레이스")
    object Linen: Material(5, "린넨")
    object Velvet: Material(6, "벨벳")
    object Suede: Material(7, "스웨이드")
    object Chiffon: Material(8, "쉬폰")
    object Silk: Material(9, "실크")
    object Wool: Material(10, "울/캐시미어")
    object Jersey: Material(11, "저지")
    object Corduroy: Material(12, "코드류이")
    object Tweed: Material(13, "트위드")
    object Padding: Material(14, "패딩")
    object Fur: Material(15, "퍼")
    object Fleece: Material(16, "플리스")

    companion object {
        fun getAllMaterial(): List<Material> {
            return Material::class.sealedSubclasses.mapNotNull { it.objectInstance as? Material }
        }
    }
}

sealed class ClothColor(val id: Int, val color: Color) {
    object Black: ClothColor(1, Color.Black)
    object Gold: ClothColor(2, GoldColor)
    object Gray: ClothColor(3, Color.Gray)
    object Green: ClothColor(4, Color.Green)
    object Navy: ClothColor(5, NavyColor)
    object Lavender: ClothColor(6, LavenderColor)
    object Red: ClothColor(7, Color.Red)
    object Mint: ClothColor(8, MintColor)
    object Beige: ClothColor(9, BeigeColor)
    object Brown: ClothColor(10, BrownColor)
    object Blue: ClothColor(11, Color.Blue)
    object SkyBlue: ClothColor(12, SkyBlueColor)
    object Silver: ClothColor(13, SilverColor)
    object Yellow: ClothColor(14, Color.Yellow)
    object Wine: ClothColor(15, WineColor)
    object Khaki: ClothColor(16, KhakiColor)
    object Purple: ClothColor(17, PurpleColor)
    object Pink: ClothColor(18, PinkColor)
    object White: ClothColor(19, Color.White)
    object Other: ClothColor(20, Color.White)

    companion object {
        fun getAllColor(): List<ClothColor> {
            return ClothColor::class.sealedSubclasses.mapNotNull { it.objectInstance as? ClothColor }
        }
    }
}