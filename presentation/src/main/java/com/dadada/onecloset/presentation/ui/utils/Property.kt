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
    object Top: Type(11, "상의", "긴팔티")
    object Blouse: Type(12, "상의", "반팔티")
    object Knit: Type(13, "상의", "셔츠/블라우스")
    object Shirt: Type(14, "상의", "니트웨어")
    object HoodT: Type(16, "상의", "후드티")
    object Sleeveless: Type(17, "상의", "민소매")

    object Jean: Type(21, "하의", "긴바지")
    object Pants: Type(22, "하의", "반바지")
    object LongSkirt: Type(23, "하의", "롱스커트")
    object MiniSkirt: Type(23, "하의", "미니스커트")

    object Coat: Type(31, "아우터", "코트")
    object Jacket: Type(32, "아우터", "재킷")
    object Jumper: Type(33, "아우터", "점퍼/ZIP-UP")
    object Padding: Type(34, "아우터", "패딩")
    object Cardigan: Type(36, "아우터", "가디건")
    object Vest: Type(37, "아우터", "베스트")

    object Dress: Type(41, "한벌옷", "원피스")
    object JumpSuit: Type(42, "한벌옷", "점프슈트")


    companion object {
        fun getAllTypes(): List<Type> {
            return Type::class.sealedSubclasses.mapNotNull { it.objectInstance as? Type }
        }

        fun getTypesByCategory(category: String): List<Type> {
            val allTypes = getAllTypes()
            return allTypes.filter { it.category == category }
        }

        fun getCategory(name: String) {

        }

    }
}

sealed class Material(val id: Int, val name: String) {
    object Leader: Material(8, "가죽")
    object Knit: Material(2, "니트")
    object Denim: Material(3, "데님")
    object Cotton: Material(1, "면/폴리")
    object Chiffon: Material(4, "쉬폰")
    object Corduroy: Material(9, "코드류이")
    object Tweed: Material(6, "트위드")
    object Padding: Material(5, "패딩")
    object Fur: Material(7, "퍼/플리스")
    object Other: Material(0, "기타")

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