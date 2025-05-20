package com.kdbrian.menusmvp.di

import com.kdbrian.menusmvp.data.repo.impl.RestaurantRepositoryImpl
import com.kdbrian.menusmvp.domain.restaurants.RestaurantRepository
import com.kdbrian.menusmvp.presentation.viewmodel.RestaurantViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val restaurantModule = module {

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(get())
    }

    viewModel {
        RestaurantViewModel(get())
    }
}