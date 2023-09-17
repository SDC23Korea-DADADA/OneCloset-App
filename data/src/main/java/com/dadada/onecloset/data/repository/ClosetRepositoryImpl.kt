package com.dadada.onecloset.data.repository

import android.util.Log
import com.dadada.onecloset.data.datasource.remote.ClosetService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import javax.inject.Inject

private const val TAG = "ClosetRepositoryImpl"
class ClosetRepositoryImpl @Inject constructor(
    private val closetService: ClosetService
) : ClosetRepository {
    override suspend fun getClosetList() : NetworkResult<List<Closet>> {
        return handleApi { closetService.getClosetList().toDomain() }
    }

    override suspend fun putCloset(closet: Closet) {
        TODO("Not yet implemented")
    }
}