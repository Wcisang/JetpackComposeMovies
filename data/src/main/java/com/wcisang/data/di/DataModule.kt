package com.wcisang.data.di

import com.wcisang.data.repository.MoviesRepositoryImpl
import com.wcisang.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bind(impl: MoviesRepositoryImpl): MoviesRepository
}