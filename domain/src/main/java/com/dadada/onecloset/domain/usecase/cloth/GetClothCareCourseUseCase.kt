package com.dadada.onecloset.domain.usecase.cloth

import com.dadada.onecloset.domain.model.ClothCareCourse
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

class GetClothCareCourseUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(material: String): NetworkResult<ClothCareCourse> {
        return closetRepository.getClothCareCourse(material)
    }
}