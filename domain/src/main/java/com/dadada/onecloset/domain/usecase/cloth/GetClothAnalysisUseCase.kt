package com.dadada.onecloset.domain.usecase.cloth

import com.dadada.onecloset.domain.model.ClothAnalysis
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class GetClothAnalysisUseCase @Inject constructor(
    private val closetRepository: ClosetRepository,
) {
    suspend operator fun invoke(image: String): NetworkResult<ClothAnalysis> {
        return closetRepository.getClothAnalysis(image)
    }
}
