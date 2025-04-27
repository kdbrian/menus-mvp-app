package com.kdbrian.menusmvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kdbrian.menusmvp.presentation.features.home.HomeScreen
import com.kdbrian.menusmvp.presentation.ui.nav.MainNavigation
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.viewmodel.MenusViewModel
import com.kdbrian.menusmvp.presentation.viewmodel.RestaurantViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.KoinContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        koinApplication {
            setContent {
                MenusMvpTheme {
                    CompositionLocalProvider {
                        MainNavigation()
                    }
                }
            }
        }
    }
}

