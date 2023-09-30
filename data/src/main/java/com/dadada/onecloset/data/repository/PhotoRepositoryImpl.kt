package com.dadada.onecloset.data.repository

import android.content.Context
import androidx.paging.PagingSource
import com.dadada.onecloset.data.datasource.local.GalleryPagingDataSource
import com.dadada.onecloset.domain.model.Photo
import com.dadada.onecloset.domain.repository.GalleryRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val galleryPagingDataSource: GalleryPagingDataSource,
    @ApplicationContext private val context: Context,
) : GalleryRepository {

    override fun getPagingSource(): PagingSource<Int, Photo> {
        return galleryPagingDataSource
    }
}