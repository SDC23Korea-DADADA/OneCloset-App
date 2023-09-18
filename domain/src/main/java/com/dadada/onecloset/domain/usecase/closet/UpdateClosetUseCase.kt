package com.dadada.onecloset.domain.usecase.closet

import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class UpdateClosetUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(closet: Closet): NetworkResult<Unit> {
        return closetRepository.updateCloset(closet)
    }
}