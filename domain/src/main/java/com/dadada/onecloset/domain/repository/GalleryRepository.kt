package com.dadada.onecloset.domain.repository

import androidx.paging.PagingSource
import com.dadada.onecloset.domain.model.Photo


interface GalleryRepository {
    fun getPagingSource(): PagingSource<Int, Photo>
}