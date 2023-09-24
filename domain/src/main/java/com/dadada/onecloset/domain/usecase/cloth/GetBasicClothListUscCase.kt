package com.dadada.onecloset.domain.usecase.cloth

import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class GetBasicClothListUscCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(): NetworkResult<List<ClothesInfo>> {
        return closetRepository.getBasicClothList()
    }
}