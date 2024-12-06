package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.MyviewDataSource
import org.sopt.and.data.datasourceimpl.MyviewDataSourceImpl
import org.sopt.and.data.repositoryimpl.MyviewRepositoryImpl
import org.sopt.and.data.service.HobbyService
import org.sopt.and.domain.repository.MyviewRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyviewModule {
    @Provides
    @Singleton
    fun provideHobbyService(retrofit: Retrofit): HobbyService {
        return retrofit.create(HobbyService::class.java)
    }

    @Provides
    @Singleton
    fun provideHobbyDataSource(hobbyService: HobbyService): MyviewDataSource {
        return MyviewDataSourceImpl(hobbyService)
    }

    @Provides
    @Singleton
    fun provideHobbyRepository(dataSource: MyviewDataSource): MyviewRepository {
        return MyviewRepositoryImpl(dataSource)
    }
}