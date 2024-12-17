package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.and.data.datasource.LoginDataSource
import org.sopt.and.data.datasourceimpl.LoginDataSourceImpl
import org.sopt.and.data.repositoryimpl.LoginRepositoryImpl
import org.sopt.and.data.service.AuthService
import org.sopt.and.domain.repository.LoginRepository

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {
    @Provides
    fun provideLoginRepository(
        dataSource: LoginDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(dataSource)
    }

    @Provides
    fun provideLoginDataSource(
        authService: AuthService
    ): LoginDataSource {
        return LoginDataSourceImpl(authService)
    }
}