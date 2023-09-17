package com.dadada.onecloset.di

import com.dadada.onecloset.data.datasource.local.PreferenceDataSource
import com.dadada.onecloset.data.datasource.remote.AccountService
import com.dadada.onecloset.data.repository.AccountRepositoryImpl
import com.dadada.onecloset.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountRepository(
        preferenceDataSource: PreferenceDataSource,
        accountService: AccountService
    ): AccountRepository {
        return AccountRepositoryImpl(
            preferenceDataSource = preferenceDataSource,
            accountService = accountService
        )
    }
}