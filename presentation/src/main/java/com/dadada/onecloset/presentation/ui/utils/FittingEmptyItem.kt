package com.dadada.onecloset.presentation.ui.utils

import com.dadada.onecloset.presentation.R

sealed class FittingEmptyItem(val icon: Int, val content: String, val upperType: String) {
    object EmptyTop: FittingEmptyItem(R.drawable.ic_t_shirt, "상의를 등록하세요.", "상의")
    object EmptyBottom: FittingEmptyItem(R.drawable.ic_jeans, "하의를 등록하세요.", "하의")
    object EmptyOne: FittingEmptyItem(R.drawable.ic_dress, "한벌옷을 등록하세요.", "한벌옷")

    companion object {
        fun getList(): List<List<FittingEmptyItem>> {
            val emptyTopBottom = listOf(EmptyTop, EmptyBottom)
            val emptyTop = listOf(EmptyTop)
            val emptyBottom = listOf(EmptyBottom)
            val emptyOne = listOf(EmptyOne)
            return listOf(emptyTopBottom, emptyTop, emptyBottom, emptyOne)
        }
    }
}
