package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.HobbyDataSource
import org.sopt.and.data.datasourceimpl.HobbyDataSourceImpl
import org.sopt.and.data.repositoryimpl.MyviewRepositoryImpl
import org.sopt.and.data.service.HobbyService
import org.sopt.and.domain.repository.MyviewRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HobbyModule {
    @Provides
    @Singleton
    fun provideHobbyService(retrofit: Retrofit): HobbyService {
        return retrofit.create(HobbyService::class.java)
    }

    @Provides
    @Singleton
    fun provideHobbyDataSource(hobbyService: HobbyService): HobbyDataSource {
        return HobbyDataSourceImpl(hobbyService)
    }

    @Provides
    @Singleton
    fun provideHobbyRepository(dataSource: HobbyDataSource): MyviewRepository {
        return MyviewRepositoryImpl(dataSource)
    }
}