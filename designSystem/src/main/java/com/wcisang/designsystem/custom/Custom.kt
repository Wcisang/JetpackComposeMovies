package com.wcisang.designsystem.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.wcisang.designsystem.R
import com.wcisang.designsystem.theme.LightGray

@Composable
fun MoviePoster(image: String, modifier: Modifier = Modifier, onClick: (() -> Unit) = {}) {
    Image(
        painter = rememberCoilPainter(request = image,
            requestBuilder = {
                transformations()
            }),
        contentDescription = "",
        modifier = modifier
            .size(130.dp, 180.dp)
            .clickable { onClick() },
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MovieLoadingPoster(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(130.dp, 180.dp),
        color = LightGray
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun MovieLoadingPosterPreview() {
    MovieLoadingPoster()
}

@Composable
fun IconToolbarArrowBack(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Icon(
        Icons.Default.ArrowBack, contentDescription = "",
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.margin_default))
            .clickable { onClick() },
        tint = Color.White
    )
}