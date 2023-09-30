package com.dadada.onecloset.domain.usecase

import androidx.paging.PagingSource
import com.dadada.onecloset.domain.model.Photo
import com.dadada.onecloset.domain.repository.GalleryRepository
import javax.inject.Inject

class GetGalleryPagingDataSourceUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
) {
    fun execute(): PagingSource<Int, Photo> {
        return galleryRepository.getPagingSource()
    }
}