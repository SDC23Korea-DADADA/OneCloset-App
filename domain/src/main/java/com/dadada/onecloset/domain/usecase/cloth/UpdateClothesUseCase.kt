package com.dadada.onecloset.domain.usecase.cloth

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class UpdateClothesUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
){
    suspend operator fun invoke(clothesInfo: ClothesInfo) : NetworkResult<Unit> {
        return closetRepository.updateClothes(clothesInfo)
    }
}