package com.dadada.onecloset.domain.usecase.closet

import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class PutClothUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(cloth: Cloth): NetworkResult<Unit> {
        return closetRepository.putCloth(cloth)
    }
}