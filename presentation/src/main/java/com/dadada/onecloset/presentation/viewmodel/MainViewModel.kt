package com.dadada.onecloset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.usecase.fitting.PutModelUseCase
import com.dadada.onecloset.presentation.ui.utils.LoadingType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val putModelUseCase: PutModelUseCase
) : ViewModel() {

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState = _loadingState.asStateFlow()

    var loadingType = LoadingType.BASIC

    fun setLoadingState(value: Boolean, type: String) {
        _loadingState.value = value
        loadingType = type
    }
}