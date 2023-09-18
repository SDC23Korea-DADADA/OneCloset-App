package com.dadada.onecloset.domain.usecase.closet

import android.util.Log
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

private const val TAG = "GetClothListUseCase"
class GetClothListUseCase @Inject constructor(
    private val closetRepository: ClosetRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<List<Cloth>> {
        Log.d(TAG, "invoke: ${closetRepository.getClothList(id)}")
        return closetRepository.getClothList(id)
    }
}