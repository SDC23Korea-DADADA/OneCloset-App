package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.ui.graphics.Color

sealed class Type(val id: Int, val category: String, val name: String) {
    object Top : Type(11, "상의", "긴팔티")
    object Blouse : Type(12, "상의", "반팔티")
    object Knit : Type(13, "상의", "셔츠/블라우스")
    object Shirt : Type(14, "상의", "니트웨어")
    object HoodT : Type(16, "상의", "후드티")
    object Sleeveless : Type(17, "상의", "민소매")

    object Jean : Type(21, "하의", "긴바지")
    object Pants : Type(22, "하의", "반바지")
    object LongSkirt : Type(23, "하의", "롱스커트")
    object MiniSkirt : Type(23, "하의", "미니스커트")

    object Coat : Type(31, "아우터", "코트")
    object Jacket : Type(32, "아우터", "재킷")
    object Jumper : Type(33, "아우터", "점퍼/짚업")
    object Padding : Type(34, "아우터", "패딩")
    object Cardigan : Type(36, "아우터", "가디건")
    object Vest : Type(37, "아우터", "베스트")

    object Dress : Type(41, "한벌옷", "원피스")
    object JumpSuit : Type(42, "한벌옷", "점프슈트")

    companion object {
        fun getAllTypes(): List<Type> {
            return Type::class.sealedSubclasses.mapNotNull { (it.objectInstance as? Type) }
        }

        fun getTypesByCategory(category: String): List<Type> {
            val allTypes = getAllTypes()
            return allTypes.filter { it.category == category }
        }
    }
}

sealed class Material(val id: Int, val name: String) {
    object Leader : Material(8, "가죽")
    object Knit : Material(2, "니트")
    object Denim : Material(3, "데님")
    object Cotton : Material(1, "면")
    object Chiffon : Material(4, "시폰")
    object Corduroy : Material(9, "코듀로이")
    object Tweed : Material(6, "트위드")
    object Padding : Material(5, "패딩")
    object Fur : Material(7, "플리스")
    object Other : Material(0, "기타")

    companion object {
        fun getAllMaterial(): List<Material> {
            return Material::class.sealedSubclasses.mapNotNull { it.objectInstance as? Material }
        }
    }
}

sealed class ClothColor(val id: Int, val color: Color, val name: String) {
    object Black : ClothColor(1, Color(0xFF000000), "블랙")
    object Gray : ClothColor(3, Color(0xFF888888), "그레이")
    object Green : ClothColor(4, Color(0xFF008000), "그린")
    object Navy : ClothColor(5, Color(0xFF000080), "네이비")
    object Lavender : ClothColor(6, Color(0xFFE6E6FA), "라벤더")
    object Red : ClothColor(7, Color(0xFFFF0000), "레드")
    object Mint : ClothColor(8, Color(0xFF92B8B1), "민트")
    object Beige : ClothColor(9, Color(0xFFD4B886), "베이지")
    object Brown : ClothColor(10, Color(0xFF964b00), "브라운")
    object Blue : ClothColor(11, Color(0xFF0000FF), "블루")
    object SkyBlue : ClothColor(12, Color(0xFF87CEEB), "스카이블루")
    object Yellow : ClothColor(14, Color(0xFFFFFF00), "옐로우")
    object Wine : ClothColor(15, Color(0xFF9F0A28), "와인")
    object Khaki : ClothColor(16, Color(0xFF8f784b), "카키")
    object Purple : ClothColor(17, Color(0xFF800080), "퍼플")
    object Pink : ClothColor(18, Color(0xFFFFC0CB), "핑크")

    object Orange : ClothColor(21, Color(0xFFFFA500), "오렌지")
    object White : ClothColor(19, Color(0xFFFFFFFF), "화이트")
    object Other : ClothColor(22, Color.Magenta, "다채색")

    companion object {
        fun getAllColor(): List<ClothColor> {
            return listOf(
                Black, Gray, Green, Navy, Lavender, Red, Mint,
                Beige, Brown, Blue, SkyBlue, Yellow, Wine, Khaki,
                Purple, Pink, Orange, White, Other,
            )
        }
    }
}

object Mode {
    const val model = "모델 등록"
    const val clothes = "의류 등록"
    const val codi = "데일리 코디 등록"
}
