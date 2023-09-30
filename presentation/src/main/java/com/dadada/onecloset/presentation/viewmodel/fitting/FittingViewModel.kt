package com.dadada.onecloset.presentation.viewmodel.fitting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingInfo
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.domain.model.fitting.FittingResult
import com.dadada.onecloset.domain.model.fitting.FittingResultForSave
import com.dadada.onecloset.domain.usecase.fitting.DeleteFittingUseCase
import com.dadada.onecloset.domain.usecase.fitting.DeleteModelUseCase
import com.dadada.onecloset.domain.usecase.fitting.GetFittingResultUseCase
import com.dadada.onecloset.domain.usecase.fitting.GetModelListUseCase
import com.dadada.onecloset.domain.usecase.fitting.PutFittingResultUseCase
import com.dadada.onecloset.domain.usecase.fitting.PutFittingResultWithDateUseCase
import com.dadada.onecloset.domain.usecase.fitting.PutModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.nio.file.Path
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FittingViewModel @Inject constructor(
    private val putModelUseCase: PutModelUseCase,
    private val getFittingResultUseCase: GetFittingResultUseCase,
    private val getModelListUseCase: GetModelListUseCase,
    private val deleteModelUseCase: DeleteModelUseCase,
    private val putFittingResultUseCase: PutFittingResultUseCase,
    private val putFittingResultWithDateUseCase: PutFittingResultWithDateUseCase,
    private val deleteFittingUseCase: DeleteFittingUseCase
) : ViewModel() {
    private lateinit var fittingInfo: FittingInfo
    var fittingResult: FittingResult = FittingResult()
    lateinit var fittingResultForSave: FittingResultForSave

    private val _modelPutState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val modelPutState = _modelPutState.asStateFlow()

    private val _modelDeleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val modelDeleteState = _modelDeleteState.asStateFlow()

    private val _modelListState = MutableStateFlow<NetworkResult<List<FittingModelInfo>>>(NetworkResult.Idle)
    val modelListState = _modelListState.asStateFlow()

    private val _fittingResultState = MutableStateFlow<NetworkResult<FittingResult>>(NetworkResult.Idle)
    val fittingResultState = _fittingResultState.asStateFlow()

    private val _fittingPutState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val fittingPutState = _fittingPutState.asStateFlow()

    private val _fittingDeleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val fittingDeleteState = _fittingDeleteState.asStateFlow()

    fun putModel(imagePath: String) = viewModelScope.launch {
        _modelPutState.value = NetworkResult.Loading
        _modelPutState.emit(putModelUseCase.invoke(imagePath))
    }

    fun deleteModel(id: String) = viewModelScope.launch {
        _modelDeleteState.value = NetworkResult.Loading
        _modelDeleteState.emit(deleteModelUseCase.invoke(id))
    }

    fun getModelList() = viewModelScope.launch {
        _modelListState.value = NetworkResult.Loading
        _modelListState.emit(getModelListUseCase.invoke())
    }

    fun getFittingResult() = CoroutineScope(Dispatchers.IO).launch {
        _fittingResultState.value = NetworkResult.Loading
        _fittingResultState.emit(getFittingResultUseCase.invoke(fittingInfo))
    }

    fun putFittingResult() = viewModelScope.launch {
        fittingResultForSave.clothesIdList= fittingResultForSave.clothesIdList.filter { it != -1 }
        _fittingPutState.value = NetworkResult.Loading
        _fittingPutState.emit(putFittingResultUseCase.invoke(fittingResultForSave))
    }

    fun putFittingResultWithDate(date: String) = viewModelScope.launch {
        fittingResultForSave.clothesIdList= fittingResultForSave.clothesIdList.filter { it != -1 }
        _fittingPutState.value = NetworkResult.Loading
        _fittingPutState.emit(putFittingResultWithDateUseCase.invoke(date, fittingResultForSave))
    }

    fun deleteFitting(id: String) = viewModelScope.launch {
        _fittingDeleteState.value = NetworkResult.Loading
        _fittingDeleteState.emit(deleteFittingUseCase.invoke(id))
    }

    fun setFittingInfoModelId(modelId: String) {
        fittingInfo = FittingInfo()
        fittingInfo.modelId = modelId
        fittingResultForSave = FittingResultForSave()
        fittingResultForSave.modelId = modelId
    }

    fun setFittingInfoBottomId(bottomId: String) {
        fittingInfo.bottomId = bottomId

    }

    fun setFittingInfoTopId(topId: String) {
        fittingInfo.upperId = topId
    }

    fun setFittingInfoOneId(oneId: String) {
        fittingInfo.onepieceId = oneId
    }

    fun resetNetworkStates() {
        _modelPutState.value = NetworkResult.Idle
        _modelDeleteState.value = NetworkResult.Idle
        _modelListState.value = NetworkResult.Idle
        _fittingResultState.value = NetworkResult.Idle
        _fittingPutState.value = NetworkResult.Idle
        _fittingDeleteState.value = NetworkResult.Idle
    }


}