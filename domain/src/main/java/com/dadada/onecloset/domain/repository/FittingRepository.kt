package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.fitting.FittingInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingResultForSave
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.domain.model.fitting.FittingResult

interface FittingRepository {

    suspend fun putModel(image: String) : NetworkResult<Unit>
    suspend fun getFittingResult(fittingInfo: FittingInfo) : NetworkResult<FittingResult>
    suspend fun getModelList() : NetworkResult<List<FittingModelInfo>>
    suspend fun deleteModel(id: String) : NetworkResult<Unit>
    suspend fun putFittingResult(fittingResult: FittingResultForSave) : NetworkResult<Unit>
    suspend fun putFittingResultWithDate(date: String, fittingResult: FittingResultForSave) : NetworkResult<Unit>
}