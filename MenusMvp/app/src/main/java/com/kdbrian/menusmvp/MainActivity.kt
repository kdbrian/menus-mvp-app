package com.kdbrian.menusmvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.presentation.components.CardWithTitleTagLineAndPreview
import com.kdbrian.menusmvp.presentation.ui.nav.MainNavigation
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.ui.theme.rubikmarkerhatchregular
import com.kdbrian.menusmvp.presentation.util.Shapes
import org.koin.compose.KoinContext
import org.koin.dsl.koinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        koinApplication {
            setContent {
                MenusMvpTheme {
                    CompositionLocalProvider {
                        KoinContext {
                            MainNavigation()
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Prev() {
    MenusMvpTheme {
//        CardWithTitleTagLineAndPreview(
//            LoremIpsum(2).values.joinToString(),
//            LoremIpsum(8).values.joinToString()
//        )
    }
}

