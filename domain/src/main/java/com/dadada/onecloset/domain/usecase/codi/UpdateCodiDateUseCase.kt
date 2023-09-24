package com.dadada.onecloset.domain.usecase.codi

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.CodiRegisterInfo
import com.dadada.onecloset.domain.repository.CodiRepository
import javax.inject.Inject

class UpdateCodiDateUseCase @Inject constructor(
    private val codiRepository: CodiRepository
) {
    suspend operator fun invoke(imagePath: String, info: CodiRegisterInfo): NetworkResult<Unit> {
        return codiRepository.updateCodi(imagePath, info)
    }
}