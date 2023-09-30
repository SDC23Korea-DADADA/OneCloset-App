package com.dadada.onecloset.data.model.closet.response

import android.os.Parcelable
import com.dadada.onecloset.domain.model.Closet
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClosetListResponse(
    val code: Int,
    val data: List<Closet>,
    val message: String
) : Parcelable