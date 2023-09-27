package com.dadada.onecloset.presentation.viewmodel.codi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.Codi
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.model.codi.CodiRegisterInfo
import com.dadada.onecloset.domain.model.codi.Fitting
import com.dadada.onecloset.domain.usecase.codi.DeleteCodiUseCase
import com.dadada.onecloset.domain.usecase.codi.GetCodiListByMonthUseCase
import com.dadada.onecloset.domain.usecase.codi.GetCodiListUseCase
import com.dadada.onecloset.domain.usecase.codi.PutCodiUseCase
import com.dadada.onecloset.domain.usecase.codi.UpdateCodiDateUseCase
import com.dadada.onecloset.domain.usecase.codi.UpdateCodiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodiViewModel @Inject constructor(
    private val putCodiUseCase: PutCodiUseCase,
    private val updateCodiDateUseCase: UpdateCodiDateUseCase,
    private val updateCodiUseCase: UpdateCodiUseCase,
    private val deleteCodiUseCase: DeleteCodiUseCase,
    private val getCodiListUseCase: GetCodiListUseCase,
    private val getCodiListByMonthUseCase: GetCodiListByMonthUseCase,

) : ViewModel() {
    val codiRegisterInfo = CodiRegisterInfo()
    var curFittingItem: Fitting = Fitting()
    var curDailyCodiItem: Codi = Codi()

    private val _codiListState = MutableStateFlow<NetworkResult<CodiList>>(NetworkResult.Idle)
    val codiListState = _codiListState.asStateFlow()

    private val _codiListByMonthState =  MutableStateFlow<NetworkResult<CodiList>>(NetworkResult.Idle)
    val codiListByMonth = _codiListByMonthState.asStateFlow()

    private val _codiPutState = MutableStateFlow<NetworkResult<Long>>(NetworkResult.Idle)
    val codiPutState = _codiPutState.asStateFlow()

    private val _codiDeleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val codiDeleteState = _codiDeleteState.asStateFlow()


    fun getCodiList() = viewModelScope.launch {
        _codiListState.value = NetworkResult.Loading
        _codiListState.emit(getCodiListUseCase.invoke())
    }

    fun getCodiListByMonth(date: String) = viewModelScope.launch {
        _codiListByMonthState.value = NetworkResult.Loading
        _codiListByMonthState.emit(getCodiListByMonthUseCase.invoke(date))
    }

    fun putCodi() = viewModelScope.launch {
        _codiPutState.value = NetworkResult.Loading
        _codiPutState.emit(putCodiUseCase.invoke(codiRegisterInfo))
    }

    fun deleteCodi() = viewModelScope.launch {
        _codiDeleteState.value = NetworkResult.Loading
        _codiDeleteState.emit(deleteCodiUseCase.invoke(curDailyCodiItem.id.toString()))
    }

    fun resetNetworkStates() {
        _codiPutState.value = NetworkResult.Idle
    }
}