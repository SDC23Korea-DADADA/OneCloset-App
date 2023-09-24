package com.dadada.onecloset.domain.usecase.fitting

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingResult
import com.dadada.onecloset.domain.model.fitting.FittingResultForSave
import com.dadada.onecloset.domain.repository.FittingRepository
import javax.inject.Inject

class PutFittingResultUseCase @Inject constructor(
    private val fittingRepository: FittingRepository
) {
    suspend operator fun invoke(fittingResultForSave: FittingResultForSave) : NetworkResult<Unit> {
        return fittingRepository.putFittingResult(fittingResultForSave)
    }
}