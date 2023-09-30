package com.dadada.onecloset.domain.usecase.fitting

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.domain.repository.FittingRepository
import javax.inject.Inject

class GetModelListUseCase @Inject constructor(
    private val fittingRepository: FittingRepository
) {
    suspend operator fun invoke() : NetworkResult<List<FittingModelInfo>> {
        return fittingRepository.getModelList()
    }
}