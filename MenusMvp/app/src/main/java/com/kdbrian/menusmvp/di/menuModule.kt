package com.kdbrian.menusmvp.di

import com.kdbrian.menusmvp.data.repo.impl.MenuRepositoryImpl
import com.kdbrian.menusmvp.domain.menus.MenuRepository
import com.kdbrian.menusmvp.presentation.viewmodel.MenusViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {
    single<MenuRepository> {
        MenuRepositoryImpl(get())
    }

    viewModel {
        MenusViewModel(get())
    }
}