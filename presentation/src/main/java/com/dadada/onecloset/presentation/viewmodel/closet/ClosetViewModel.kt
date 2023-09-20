package com.dadada.onecloset.presentation.viewmodel.closet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.usecase.closet.DeleteClosetUseCase
import com.dadada.onecloset.domain.usecase.closet.GetClosetListUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetClothListUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetClothUseCase
import com.dadada.onecloset.domain.usecase.closet.PutClosetUseCase
import com.dadada.onecloset.domain.usecase.cloth.PutClothUseCase
import com.dadada.onecloset.domain.usecase.closet.UpdateClosetUseCase
import com.dadada.onecloset.domain.usecase.cloth.DeleteClothUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetBasicClothListUscCase
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
    private val getBasicClothListUscCase: GetBasicClothListUscCase,
    private val getClothListUseCase: GetClothListUseCase,
    private val getClothUseCase: GetClothUseCase,
    private val putClothUseCase: PutClothUseCase,
    private val deleteClothUseCase: DeleteClothUseCase
) : ViewModel() {
    private val _closetListState = MutableStateFlow<NetworkResult<List<Closet>>>(NetworkResult.Idle)
    val closetListState = _closetListState.asStateFlow()

    private val _clothListState = MutableStateFlow<NetworkResult<List<Cloth>>>(NetworkResult.Idle)
    val clothListState = _clothListState.asStateFlow()

    private val _networkResultState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val networkResultState = _networkResultState.asStateFlow()

    private val _clothState = MutableStateFlow<NetworkResult<Cloth>>(NetworkResult.Idle)
    val clothState = _clothState.asStateFlow()

    private val _clothRegisterIdState = MutableStateFlow<NetworkResult<Long>>(NetworkResult.Idle)
    val clothRegisterIdState = _clothRegisterIdState.asStateFlow()

    private val _clothDeleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val clothDeleteState = _clothDeleteState.asStateFlow()


    private lateinit var selectedClosetId: String
    var cloth: Cloth = Cloth()

    fun getClosetList() = viewModelScope.launch {
        _closetListState.value = NetworkResult.Loading
        _closetListState.emit(getClosetListUseCase.invoke())
        _clothListState.value = NetworkResult.Idle
    }

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

    fun getBasicClothList() = viewModelScope.launch {
        _clothListState.value = NetworkResult.Loading
        _clothListState.emit(getBasicClothListUscCase.invoke())
    }

    fun getClothList() = viewModelScope.launch {
        _clothListState.value = NetworkResult.Loading
        _clothListState.emit(getClothListUseCase.invoke(selectedClosetId))
    }

    fun getCloth(clothId: String) = viewModelScope.launch {
        _clothState.emit(getClothUseCase.invoke(clothId))
    }

    fun putCloth() = viewModelScope.launch {
        _clothRegisterIdState.value = NetworkResult.Loading
        _clothRegisterIdState.emit(putClothUseCase.invoke(cloth))
    }

    fun deleteCloth(id: String) = viewModelScope.launch {
        _clothDeleteState.value = NetworkResult.Loading
        _clothDeleteState.emit(deleteClothUseCase.invoke(id))
    }

    fun setSelectedId(id: String) {
        selectedClosetId = id
    }
}