package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.domain.model.ClothAnalysis
import com.dadada.onecloset.domain.model.ClothCareCourse
import com.dadada.onecloset.domain.model.NetworkResult

interface ClosetRepository {
    suspend fun getClosetList(): NetworkResult<List<Closet>>
    suspend fun putCloset(closet: Closet): NetworkResult<Unit>
    suspend fun updateCloset(closet: Closet): NetworkResult<Unit>
    suspend fun deleteCloset(id: String): NetworkResult<Unit>

    suspend fun getBasicClothList() : NetworkResult<List<ClothesInfo>>
    suspend fun getClothList(id: String) : NetworkResult<List<ClothesInfo>>
    suspend fun putCloth(clothesInfoRequest: ClothesInfo) : NetworkResult<Long>
    suspend fun getCloth(id: String) : NetworkResult<ClothesInfo>
    suspend fun deleteCloth(id: String) : NetworkResult<Unit>
    suspend fun getClothAnalysis(image: String) : NetworkResult<ClothAnalysis>
    suspend fun getClothCareCourse(material: String) : NetworkResult<ClothCareCourse>
    suspend fun updateClothes(clothesInfo: ClothesInfo) : NetworkResult<Unit>
}