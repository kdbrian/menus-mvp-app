package com.kdbrian.menusmvp.presentation.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
) {

    var showFilters by remember { mutableStateOf(false) }
    val (searchString, setSearchString) = remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                IconButton(onClick = onClose) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }

                Spacer(Modifier.width(6.dp))

                TextField(
                    value = searchString,
                    onValueChange = setSearchString,
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = Shapes.rounded32Dp,
                    trailingIcon = {
                        IconButton(onClick = { showFilters = !showFilters }) {
                            Icon(
                                painter = painterResource(R.drawable.filter_list),
                                contentDescription = null
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = LoremIpsum(3).values.joinToString(),
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

        }

        item { Spacer(Modifier.height(30.dp)) }


        item {
            GroupedItemsWithTitleAndRoundedBorder(
                title = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append("Recent")
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
                    }
                },
                content = {
                    repeat(4) {
                        Text(
                            text = "search ${it + 1}",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(shape = Shapes.rounded12Dp, color = Color.LightGray)
                                .padding(6.dp)
                        )
                    }
                }
            )
        }

    }

    if (showFilters)
        ModalBottomSheet(onDismissRequest = {
            showFilters = !showFilters
        }) { }

}

@Preview
@Composable
private fun SearchScreenPrev() {
    MenusMvpTheme {
        SearchScreen()
    }
}