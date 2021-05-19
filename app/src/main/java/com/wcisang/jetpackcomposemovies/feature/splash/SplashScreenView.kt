package com.wcisang.jetpackcomposemovies.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wcisang.designsystem.theme.JetpackComposeMoviesTheme
import com.wcisang.jetpackcomposemovies.R

@Composable
fun SplashScreenView() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(
                id = R.drawable.logo_globoplay
            ),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun SplashScreenViewPreview() {
    JetpackComposeMoviesTheme() {
        SplashScreenView()
    }
}