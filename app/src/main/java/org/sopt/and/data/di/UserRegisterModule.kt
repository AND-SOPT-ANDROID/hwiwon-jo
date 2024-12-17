package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.datasourceimpl.UserDataSourceImpl
import org.sopt.and.data.repositoryimpl.UserRegistrationRepositoryImpl
import org.sopt.and.data.service.AuthService
import org.sopt.and.domain.repository.UserRegistrationRepository

@Module
@InstallIn(ViewModelComponent::class)
object UserRegisterModule {
    @Provides
    fun provideUserRegistrationRepository(
        dataSource: UserDataSource
    ): UserRegistrationRepository {
        return UserRegistrationRepositoryImpl(dataSource)
    }

    @Provides
    fun provideUserRegistrationDataSource(
        authService: AuthService
    ): UserDataSource {
        return UserDataSourceImpl(authService)
    }
}