package com.dadada.onecloset.di

import android.content.ContentResolver
import android.content.Context
import com.dadada.onecloset.data.datasource.local.PreferenceDataSource
import com.dadada.onecloset.data.datasource.remote.AccountService
import com.dadada.onecloset.data.datasource.remote.ClosetService
import com.dadada.onecloset.data.datasource.remote.FittingService
import com.dadada.onecloset.data.repository.AccountRepositoryImpl
import com.dadada.onecloset.data.repository.ClosetRepositoryImpl
import com.dadada.onecloset.data.repository.FittingRepositoryImpl
import com.dadada.onecloset.domain.repository.AccountRepository
import com.dadada.onecloset.domain.repository.ClosetRepository
import com.dadada.onecloset.domain.repository.FittingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideClosetRepository(
        closetService: ClosetService,
        @ApplicationContext context: Context
    ): ClosetRepository {
        return ClosetRepositoryImpl(
            closetService = closetService,
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideFittingRepository(
        fittingService: FittingService,
        @ApplicationContext context: Context
    ): FittingRepository {
        return FittingRepositoryImpl(
            fittingService = fittingService,
            context = context
        )
    }
}