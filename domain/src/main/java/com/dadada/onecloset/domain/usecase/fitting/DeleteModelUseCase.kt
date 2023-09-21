package com.dadada.onecloset.domain.usecase.fitting

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.FittingRepository
import javax.inject.Inject

class DeleteModelUseCase @Inject constructor(
    private val fittingRepository: FittingRepository
) {
    suspend operator fun invoke(id: String) : NetworkResult<Unit> {
        return fittingRepository.deleteModel(id)
    }
}