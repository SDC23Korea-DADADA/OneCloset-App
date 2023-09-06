package com.dadada.onecloset.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dadada.onecloset.domain.model.Photo
import com.dadada.onecloset.domain.usecase.GetGalleryPagingDataSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PhotoViewModel"

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getGalleryPagingDataSourceUseCase: GetGalleryPagingDataSourceUseCase
) : ViewModel() {
    private val _photoList = MutableStateFlow<PagingData<Photo>>(PagingData.empty())
    val photoList: StateFlow<PagingData<Photo>>
        get() = _photoList.asStateFlow()

    fun getPagingPhotos() = viewModelScope.launch {
        _photoList.value = PagingData.empty()
        Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                getGalleryPagingDataSourceUseCase.execute()
            },
        ).flow.cachedIn(viewModelScope).collectLatest {
            Log.d(TAG, "getPagingPhotos: $it")
            _photoList.value = it
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val PAGING_SIZE = 28
    }
}

