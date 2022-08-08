package com.tharindu.recipes.di

import com.tharindu.recipes.data.provider.RecipeProvider
import com.tharindu.recipes.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBankRepository(provider: RecipeProvider): RecipeRepository

}
