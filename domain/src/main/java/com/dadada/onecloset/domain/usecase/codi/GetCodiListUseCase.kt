package com.dadada.onecloset.domain.usecase.codi

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.repository.CodiRepository
import javax.inject.Inject

class GetCodiListUseCase @Inject constructor(
    private val codiRepository: CodiRepository
) {
    suspend operator fun invoke(): NetworkResult<CodiList> {
        return codiRepository.getCodiList()
    }
}