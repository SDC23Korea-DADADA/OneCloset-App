package com.dadada.onecloset.domain.usecase.closet

import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class GetClothListUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<List<Cloth>> {
        return closetRepository.getClothList(id)
    }
}