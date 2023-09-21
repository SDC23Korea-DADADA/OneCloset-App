package com.dadada.onecloset.domain.usecase.fitting

import com.dadada.onecloset.domain.model.fitting.FittingInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.fitting.FittingResult
import com.dadada.onecloset.domain.repository.FittingRepository
import javax.inject.Inject

class GetFittingResultUseCase @Inject constructor(
    private val fittingRepository: FittingRepository
) {
    suspend operator fun invoke(fittingInfo: FittingInfo) : NetworkResult<FittingResult> {
        return fittingRepository.getFittingResult(fittingInfo)
    }
}