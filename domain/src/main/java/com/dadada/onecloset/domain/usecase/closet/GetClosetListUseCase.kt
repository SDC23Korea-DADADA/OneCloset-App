package com.dadada.onecloset.domain.usecase.closet

import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class GetClosetListUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Closet>> {
        return closetRepository.getClosetList()
    }
}