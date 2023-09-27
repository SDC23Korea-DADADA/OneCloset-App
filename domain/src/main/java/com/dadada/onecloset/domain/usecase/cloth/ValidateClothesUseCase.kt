package com.dadada.onecloset.domain.usecase.cloth

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class ValidateClothesUseCase @Inject constructor(
    private val clothRepository: ClosetRepository
){
    suspend operator fun invoke(imagePath: String) : NetworkResult<Boolean> {
        return clothRepository.checkClothes(imagePath)
    }

}