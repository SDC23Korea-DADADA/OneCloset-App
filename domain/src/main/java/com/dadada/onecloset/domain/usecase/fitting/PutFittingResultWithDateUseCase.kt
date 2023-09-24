package com.dadada.onecloset.domain.usecase.fitting

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingResultForSave
import com.dadada.onecloset.domain.repository.FittingRepository
import javax.inject.Inject

class PutFittingResultWithDateUseCase @Inject constructor(
    private val fittingRepository: FittingRepository
) {
    suspend operator fun invoke(date: String, fittingResultForSave: FittingResultForSave) : NetworkResult<Unit> {
        return fittingRepository.putFittingResultWithDate(date, fittingResultForSave)
    }
}