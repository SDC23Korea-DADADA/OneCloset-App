package com.dadada.onecloset.presentation.viewmodel.closet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.domain.model.ClothAnalysis
import com.dadada.onecloset.domain.model.ClothCareCourse
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.usecase.closet.DeleteClosetUseCase
import com.dadada.onecloset.domain.usecase.closet.GetClosetListUseCase
import com.dadada.onecloset.domain.usecase.closet.PutClosetUseCase
import com.dadada.onecloset.domain.usecase.closet.UpdateClosetUseCase
import com.dadada.onecloset.domain.usecase.cloth.DeleteClothUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetBasicClothListUscCase
import com.dadada.onecloset.domain.usecase.cloth.GetClothAnalysisUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetClothCareCourseUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetClothListUseCase
import com.dadada.onecloset.domain.usecase.cloth.GetClothUseCase
import com.dadada.onecloset.domain.usecase.cloth.PutClothUseCase
import com.dadada.onecloset.domain.usecase.cloth.UpdateClothesUseCase
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
    private val deleteClothUseCase: DeleteClothUseCase,
    private val putClothAnalysisUseCase: GetClothAnalysisUseCase,
    private val getClothCareCourseUseCase: GetClothCareCourseUseCase,
    private val updateClothesUseCase: UpdateClothesUseCase
) : ViewModel() {
    private val _closetListState = MutableStateFlow<NetworkResult<List<Closet>>>(NetworkResult.Idle)
    val closetListState = _closetListState.asStateFlow()

    private val _clothListState = MutableStateFlow<NetworkResult<List<ClothesInfo>>>(NetworkResult.Idle)
    val clothListState = _clothListState.asStateFlow()

    private val _networkResultState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val networkResultState = _networkResultState.asStateFlow()

    private val _clothState = MutableStateFlow<NetworkResult<ClothesInfo>>(NetworkResult.Idle)
    val clothState = _clothState.asStateFlow()

    private val _clothRegisterIdState = MutableStateFlow<NetworkResult<Long>>(NetworkResult.Idle)
    val clothRegisterIdState = _clothRegisterIdState.asStateFlow()

    private val _clothDeleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val clothDeleteState = _clothDeleteState.asStateFlow()

    private val _clothesUpdateState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val clothesUpdatState = _clothesUpdateState.asStateFlow()

    private val _clothAnalysisState =
        MutableStateFlow<NetworkResult<ClothAnalysis>>(NetworkResult.Idle)
    val clothAnalysisState = _clothAnalysisState.asStateFlow()

    private val _clothCareCourseState =
        MutableStateFlow<NetworkResult<ClothCareCourse>>(NetworkResult.Idle)
    val clothCareCourseState = _clothCareCourseState.asStateFlow()

    private lateinit var selectedClosetId: String
    var clothesInfo = ClothesInfo()

    fun getClosetList() = viewModelScope.launch {
        _closetListState.value = NetworkResult.Loading
        _closetListState.emit(getClosetListUseCase.invoke())
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
        _clothState.value = NetworkResult.Loading
        _clothState.emit(getClothUseCase.invoke(clothId))
    }

    fun putCloth() = viewModelScope.launch {
        _clothRegisterIdState.value = NetworkResult.Loading
        _clothRegisterIdState.emit(putClothUseCase.invoke(clothesInfo))
    }

    fun deleteCloth(id: String) = viewModelScope.launch {
        _clothDeleteState.value = NetworkResult.Loading
        _clothDeleteState.emit(deleteClothUseCase.invoke(id))
    }

    fun setSelectedId(id: String) {
        selectedClosetId = id
    }

    fun putClothAnalysis(image: String) = viewModelScope.launch {
        _clothAnalysisState.value = NetworkResult.Loading
        _clothAnalysisState.emit(putClothAnalysisUseCase.invoke(image))
    }

    fun getClothCare() = viewModelScope.launch {
        _clothCareCourseState.value = NetworkResult.Loading
        _clothCareCourseState.emit(getClothCareCourseUseCase.invoke(clothesInfo.material))
    }

    fun updateClothes(clothesInfo: ClothesInfo) = viewModelScope.launch {
        _clothesUpdateState.value = NetworkResult.Loading
        _clothesUpdateState.emit(updateClothesUseCase.invoke(clothesInfo))
    }

    fun resetNetworkStates() {
        _closetListState.value = NetworkResult.Idle
        _clothListState.value = NetworkResult.Idle
        _networkResultState.value = NetworkResult.Idle
        _clothState.value = NetworkResult.Idle
        _clothRegisterIdState.value = NetworkResult.Idle
        _clothDeleteState.value = NetworkResult.Idle
        _clothAnalysisState.value = NetworkResult.Idle
        _clothCareCourseState.value = NetworkResult.Idle
        _clothesUpdateState.value = NetworkResult.Idle
    }

}