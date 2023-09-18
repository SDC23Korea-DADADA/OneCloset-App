package com.dadada.onecloset.presentation.viewmodel.closet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.usecase.closet.DeleteClosetUseCase
import com.dadada.onecloset.domain.usecase.closet.GetClosetListUseCase
import com.dadada.onecloset.domain.usecase.closet.GetClothListUseCase
import com.dadada.onecloset.domain.usecase.closet.PutClosetUseCase
import com.dadada.onecloset.domain.usecase.closet.UpdateClosetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor(
    private val getClosetListUseCase: GetClosetListUseCase,
    private val putClosetUseCase: PutClosetUseCase,
    private val deleteClosetUseCase: DeleteClosetUseCase,
    private val updateClosetUseCase: UpdateClosetUseCase,
    private val getClothListUseCase: GetClothListUseCase
) : ViewModel() {
    private val _closetListState = MutableStateFlow<NetworkResult<List<Closet>>>(NetworkResult.Idle)
    val closetListState = _closetListState.asStateFlow()

    private val _clothListState = MutableStateFlow<NetworkResult<List<Cloth>>>(NetworkResult.Idle)
    val clothListState = _clothListState.asStateFlow()

    private val _networkResultState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val networkResultState = _networkResultState.asStateFlow()

    private lateinit var selectedId: String
    private lateinit var selectedCloth: Cloth

    fun getClosetList() = viewModelScope.launch {
        _closetListState.value = NetworkResult.Loading
        _closetListState.emit(getClosetListUseCase.invoke())
    }

    private val TAG = "ClosetViewModel"
    fun putCloset(closet: Closet) = viewModelScope.launch {
        _networkResultState.value = NetworkResult.Loading
        _networkResultState.emit(putClosetUseCase.invoke(closet))
    }

    fun updateCloset(closet: Closet) = viewModelScope.launch {
        _networkResultState.emit(updateClosetUseCase.invoke(closet))
    }

    fun deleteCloset(id: String) = viewModelScope.launch {
        _networkResultState.emit(deleteClosetUseCase.invoke(id))
    }

    fun getClothList() = viewModelScope.launch {
        _clothListState.value = NetworkResult.Loading
        _clothListState.emit(getClothListUseCase.invoke(selectedId))
    }

    fun setSelectedId(id: String) {
        selectedId = id
    }

    fun setSelectedCloth(cloth: Cloth) {
        selectedCloth = cloth
    }

    fun getSelectedCloth(): Cloth {
        return selectedCloth
    }

}