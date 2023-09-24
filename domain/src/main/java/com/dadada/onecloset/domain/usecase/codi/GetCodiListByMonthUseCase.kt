package com.dadada.onecloset.domain.usecase.codi

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.repository.CodiRepository
import javax.inject.Inject

class GetCodiListByMonthUseCase @Inject constructor(
    private val codiRepository: CodiRepository
) {
    suspend operator fun invoke(date: String): NetworkResult<CodiList> {
        return codiRepository.getCodiListByMonth(date)
    }
}