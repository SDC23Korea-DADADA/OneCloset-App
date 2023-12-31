package com.dadada.onecloset.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dadada.onecloset.domain.model.Photo
import com.dadada.onecloset.domain.usecase.GetGalleryPagingDataSourceUseCase
import com.dadada.onecloset.presentation.ui.utils.Mode
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
    var curMode = Mode.clothes

    private val _photoList = MutableStateFlow<PagingData<Photo>>(PagingData.empty())
    val photoList: StateFlow<PagingData<Photo>>
        get() = _photoList.asStateFlow()

    private val _isCheckedIdx = MutableStateFlow(-1)
    val isCheckedIdx: StateFlow<Int> get() = _isCheckedIdx.asStateFlow()
    fun setCheckedIndex(index: Int) {
        _isCheckedIdx.value = index
    }

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

