package com.example.showmoview.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.showmoview.android.R

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier, uiState: DetailScreenState
) {

    uiState.movie?.let { movie ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(320.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp)
            ) {
                Text(
                    text = movie.title ?: "NA",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.height(10.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play_button),
                        contentDescription = null, tint = Color.Black
                    )
                    Spacer(modifier = modifier.height(4.dp))
                    Text(text = "Start watching....")
                }
                Spacer(modifier = modifier.height(14.dp))
                Text(
                    text = movie.releaseDate?.uppercase() ?: "NA",
                    style = MaterialTheme.typography.overline
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(text = movie.description ?: "NA", style = MaterialTheme.typography.body2)


            }
        }

    }

    if (uiState.loading == true) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Color.Red)
        }
    }
}