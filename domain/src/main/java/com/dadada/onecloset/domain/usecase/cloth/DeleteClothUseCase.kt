package com.dadada.onecloset.domain.usecase.cloth

import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class DeleteClothUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<Unit> {
        return closetRepository.deleteCloth(id)
    }
}