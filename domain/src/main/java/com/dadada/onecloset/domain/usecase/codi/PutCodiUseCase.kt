package com.dadada.onecloset.domain.usecase.codi

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.CodiRegisterInfo
import com.dadada.onecloset.domain.repository.CodiRepository
import javax.inject.Inject

class PutCodiUseCase @Inject constructor(
    private val codiRepository: CodiRepository
) {
    suspend operator fun invoke(imagePath: String, info: CodiRegisterInfo): NetworkResult<Long> {
        return codiRepository.putCodi(imagePath, info)
    }
}