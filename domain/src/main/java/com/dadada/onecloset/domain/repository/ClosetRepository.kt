package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.NetworkResult

interface ClosetRepository {
    suspend fun getClosetList(): NetworkResult<List<Closet>>
    suspend fun putCloset(closet: Closet): NetworkResult<Unit>
    suspend fun updateCloset(closet: Closet): NetworkResult<Unit>
    suspend fun deleteCloset(id: String): NetworkResult<Unit>
}