package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.Codi
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.model.codi.CodiRegisterInfo
import com.dadada.onecloset.domain.model.codi.CodiUpdateDate

interface CodiRepository {
    suspend fun putCodi(info: CodiRegisterInfo) : NetworkResult<Long>
    suspend fun updateCodi(imagePath: String, info: CodiRegisterInfo) : NetworkResult<Unit>
    suspend fun deleteCodi(id: String) : NetworkResult<Unit>
    suspend fun updateCodiDate(info: CodiUpdateDate) : NetworkResult<Unit>

    suspend fun getCodiList() : NetworkResult<CodiList>
    suspend fun getCodiListByMonth(date: String) : NetworkResult<CodiList>
}