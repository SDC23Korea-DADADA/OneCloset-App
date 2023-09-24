package com.dadada.onecloset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.usecase.fitting.PutModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val putModelUseCase: PutModelUseCase
) : ViewModel() {

    private val _modelPutState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val modelPutState = _modelPutState.asStateFlow()

    fun putModel(imagePath: String) = viewModelScope.launch {
        _modelPutState.value = NetworkResult.Loading
        _modelPutState.emit(putModelUseCase.invoke(imagePath))
    }
}