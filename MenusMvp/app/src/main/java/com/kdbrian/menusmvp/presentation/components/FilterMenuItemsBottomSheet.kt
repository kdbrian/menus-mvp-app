package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes


@Composable
fun FilterMenuItemsBottomSheet(modifier: Modifier = Modifier) {

    val (name, setName) = remember { mutableStateOf("") }
    val (priceFrom, setPriceFrom) = remember { mutableDoubleStateOf(0.0) }
    val (priceTo, setPriceTo) = remember { mutableDoubleStateOf(0.0) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val selectedCategoriesAndTags = remember { mutableListOf<Int>() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Column {
            Text(text = "Name", style = MaterialTheme.typography.titleLarge)
            MenusMvpOutlinedTextField(
                query = name,
                onQueryChange = setName,
                placeHolder = "item name",
                keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyBoardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
            )
        }

        Column {

            Text(text = "Price", style = MaterialTheme.typography.titleLarge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                MenusMvpOutlinedTextField(
                    query = priceFrom.toString(),
                    onQueryChange = { setPriceFrom(it.toDouble()) },
                    placeHolder = "price from",
                    modifier = Modifier.weight(1f),
                    keyBoardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Decimal
                    ),
                    keyBoardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    })
                )


                MenusMvpOutlinedTextField(
                    query = priceTo.toString(),
                    onQueryChange = { setPriceTo(it.toDouble()) },
                    placeHolder = "price to",
                    modifier = Modifier.weight(1f),
                    keyBoardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Decimal
                    ),
                    keyBoardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    })
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            items(10) {
                FilterChip(
                    selected = selectedCategoriesAndTags.contains(it),
                    onClick = {
                        if (!selectedCategoriesAndTags.contains(it))
                            selectedCategoriesAndTags.add(it)
                        else
                            selectedCategoriesAndTags.remove(it)
                    },
                    label = {
                        Text(
                            text = LoremIpsum(2).values.joinToString()
                        )
                    },
                    modifier = Modifier.padding(4.dp),
                    shape = Shapes.rounded12Dp,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color.LightGray,
                        selectedLabelColor = Color.White,

                    )
                )
            }
        }


        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Apply")
        }

    }
}

@Preview
@Composable
private fun FilterMenuItemsBottomSheetPrev() {
    MenusMvpTheme {
        FilterMenuItemsBottomSheet()
    }
}