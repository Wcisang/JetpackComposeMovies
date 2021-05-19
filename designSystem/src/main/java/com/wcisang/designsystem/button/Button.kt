package com.wcisang.designsystem.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    MovieButton(modifier, text, icon, Color.White, Color(0xFF454545), onClick)
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    MovieButton(modifier, text, icon, Color.Black, Color(0xFFcccccc), onClick)
}

@Composable
private fun MovieButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null)
                Icon(icon, contentDescription = "", tint = contentColor)
            Text(text = text, color = contentColor)
        }
    }
}