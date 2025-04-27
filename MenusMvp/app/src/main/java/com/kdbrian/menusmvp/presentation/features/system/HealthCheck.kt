package com.kdbrian.menusmvp.presentation.features.system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Resource
import com.kdbrian.menusmvp.presentation.viewmodel.SystemHealthCheckViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object HealthCheck

@Composable
fun HealthCheck(
    modifier: Modifier = Modifier,
    systemHealthCheckViewModel: SystemHealthCheckViewModel = koinViewModel(),
    onProceed: () -> Unit = {}
) {

    val healthState by systemHealthCheckViewModel.systemStatus.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        when (healthState) {
            is Resource.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Error : ${healthState.message}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    IconButton(onClick = systemHealthCheckViewModel::refresh) {
                        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
                    }
                }
            }

            is Resource.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Checking System please wait",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            is Resource.Nothing -> {
                Text(text = "Nothing Here", style = MaterialTheme.typography.titleMedium)
            }

            is Resource.Success -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        tint = Color.Green,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = healthState.data.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )

                    TextButton(onClick = onProceed) {
                        Text(
                            text = "Proceed",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }


    }

}

@Preview
@Composable
private fun HealthCheckPrev() {
    MenusMvpTheme {
        HealthCheck()
    }
}