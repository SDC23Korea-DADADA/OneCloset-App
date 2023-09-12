package com.dadada.onecloset.di

import com.dadada.onecloset.data.repository.AccountRepositoryImpl
import com.dadada.onecloset.data.repository.PhotoRepositoryImpl
import com.dadada.onecloset.domain.repository.AccountRepository
import com.dadada.onecloset.domain.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun bindPhotoRepository(galleryRepositoryImpl: PhotoRepositoryImpl): GalleryRepository
}