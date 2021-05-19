package com.wcisang.designsystem.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MovieTextBody1(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    textColor: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = textColor,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun MovieTextBody2(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = textColor,
        modifier = modifier
    )
}

@Composable
fun MovieTextH5(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        color = textColor,
        modifier = modifier
    )
}

@Composable
fun MovieTextH6(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        color = textColor,
        modifier = modifier
    )
}