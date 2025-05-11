package com.kdbrian.menusmvp.presentation.features.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    onSignIn : (String,String) -> Unit = {_,_->}
) {

    val defaultColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){

        TextField(
            value = email,
            onValueChange = setEmail,
            modifier = Modifier.fillMaxWidth(),
            shape = Shapes.rounded24Dp,
            colors = defaultColors
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = setPassword,
            modifier = Modifier.fillMaxWidth(),
            shape = Shapes.rounded24Dp,
            colors = defaultColors
        )


        Button(
            onClick = {}
        ) {
            Text(text = "Sign In")
        }

    }

}

@Preview
@Composable
private fun SignInPrev() {
    MenusMvpTheme {
        SignIn()
    }
}