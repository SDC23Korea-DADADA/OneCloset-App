package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.ClothAnalysis
import com.dadada.onecloset.domain.model.ClothCareCourse
import com.dadada.onecloset.domain.model.NetworkResult

interface ClosetRepository {
    suspend fun getClosetList(): NetworkResult<List<Closet>>
    suspend fun putCloset(closet: Closet): NetworkResult<Unit>
    suspend fun updateCloset(closet: Closet): NetworkResult<Unit>
    suspend fun deleteCloset(id: String): NetworkResult<Unit>

    suspend fun getBasicClothList() : NetworkResult<List<Cloth>>
    suspend fun getClothList(id: String) : NetworkResult<List<Cloth>>
    suspend fun putCloth(cloth: Cloth) : NetworkResult<Long>
    suspend fun getCloth(id: String) : NetworkResult<Cloth>
    suspend fun deleteCloth(id: String) : NetworkResult<Unit>
    suspend fun getClothAnalysis(image: String) : NetworkResult<ClothAnalysis>
    suspend fun getClothCareCourse(material: String) : NetworkResult<ClothCareCourse>
}