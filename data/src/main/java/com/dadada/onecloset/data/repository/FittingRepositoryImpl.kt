package com.dadada.onecloset.data.repository

import android.content.Context
import com.dadada.onecloset.data.datasource.remote.FittingService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.Converter
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.fitting.FittingInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingResultForSave
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.domain.model.fitting.FittingResult
import com.dadada.onecloset.domain.repository.FittingRepository
import javax.inject.Inject

class FittingRepositoryImpl @Inject constructor(
    private val fittingService: FittingService,
    private val context: Context
) : FittingRepository {
    override suspend fun putModel(imagePath: String): NetworkResult<Unit> {
        return handleApi { fittingService.putModel(Converter.createMultipartBodyPart(context, imagePath)) }
    }

    override suspend fun getFittingResult(fittingInfo: FittingInfo): NetworkResult<FittingResult> {
        return handleApi { fittingService.getVirtualFittingResult(fittingInfo).toDomain() }
    }

    override suspend fun getModelList(): NetworkResult<List<FittingModelInfo>> {
        return handleApi { fittingService.getModelList().toDomain() }
    }

    override suspend fun deleteModel(id: String): NetworkResult<Unit> {
        return handleApi { fittingService.deleteModel(id) }
    }

    override suspend fun putFittingResult(fittingResult: FittingResultForSave): NetworkResult<Unit> {
        return handleApi { fittingService.putFittingResult(fittingResult) }
    }

    override suspend fun putFittingResultWithDate(
        date: String,
        fittingResult: FittingResultForSave
    ): NetworkResult<Unit> {
        return handleApi { fittingService.putFittingResultWithDate(date, fittingResult) }
    }

    override suspend fun deleteFitting(id: String): NetworkResult<Unit> {
        return handleApi { fittingService.deleteFitting(id) }
    }

}