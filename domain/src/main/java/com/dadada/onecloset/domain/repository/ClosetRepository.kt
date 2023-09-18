package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult

interface ClosetRepository {
    suspend fun getClosetList(): NetworkResult<List<Closet>>
    suspend fun putCloset(closet: Closet): NetworkResult<Unit>
    suspend fun updateCloset(closet: Closet): NetworkResult<Unit>
    suspend fun deleteCloset(id: String): NetworkResult<Unit>

    suspend fun getClothList(id: String) : NetworkResult<List<Cloth>>
    suspend fun putCloth(cloth: Cloth) : NetworkResult<Unit>
}