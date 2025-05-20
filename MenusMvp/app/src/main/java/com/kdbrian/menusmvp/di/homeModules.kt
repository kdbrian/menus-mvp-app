package com.kdbrian.menusmvp.di

import com.kdbrian.menusmvp.presentation.viewmodel.HomeScreenModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {

    viewModel {
        HomeScreenModel(
            get(),
            get()
        )
    }
}