package com.wcisang.jetpackcomposemovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.wcisang.designsystem.theme.JetpackComposeMoviesTheme
import com.wcisang.jetpackcomposemovies.navigation.MovieNavigationApplication
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeMoviesTheme {
                MovieNavigationApplication()
            }
        }
    }
}