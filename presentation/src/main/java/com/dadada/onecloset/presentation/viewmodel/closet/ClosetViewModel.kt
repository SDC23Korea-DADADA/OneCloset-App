package com.dadada.onecloset.presentation.viewmodel.closet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.usecase.closet.GetClosetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor(
    private val getClosetListUseCase: GetClosetListUseCase
) : ViewModel() {
    private val _closetListState = MutableStateFlow<NetworkResult<List<Closet>>>(NetworkResult.Idle)
    val closetListState = _closetListState.asStateFlow()

    fun getClosetList() = viewModelScope.launch {
        _closetListState.emit(getClosetListUseCase.invoke())
    }
}