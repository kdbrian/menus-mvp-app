package com.kdbrian.menusmvp.di

import com.kdbrian.menusmvp.data.repo.impl.MenuSectionRepositoryImpl
import com.kdbrian.menusmvp.domain.menus.MenuSectionRepository
import com.kdbrian.menusmvp.presentation.viewmodel.MenuSectionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val menuSectionModule = module {
    single<MenuSectionRepository> {
        MenuSectionRepositoryImpl(get())
    }

    viewModel<MenuSectionViewModel> {
        MenuSectionViewModel(get())
    }


}