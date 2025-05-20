package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.util.Shapes

@Composable
fun MenusMvpOutlinedTextField(
    modifier: Modifier = Modifier,
    keyBoardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: ImageVector? = null,
    keyBoardOptions: KeyboardOptions = KeyboardOptions.Default,
    query: String,
    placeHolder: String,
    onQueryChange: (String) -> Unit,
) {

    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = Shapes.rounded12Dp,
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.White,
            focusedPlaceholderColor = Color.White,
            errorPlaceholderColor = Color.Red.copy(alpha = 0.6f),
        ),
        keyboardActions = keyBoardActions,
        keyboardOptions = keyBoardOptions,
        singleLine = true,
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = placeHolder
                )
            }
        }
    )
}