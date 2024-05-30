package com.dentonstudio.rickandmorty.util

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@SuppressLint("RememberReturnType")
@Composable
fun GifScreen() {
    // MutableState is used to track Gif URLs

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            val view = remember { ImageView(context) }

            // Load Gif with Glide library
            DisposableEffect(context) {
                Glide.with(context)
                    .asGif()
                    .load("https://imager-prod.onquidd.com/quidds/48548-img-l-1532975350.gif")
                    .into(view)
                onDispose {
                    // Cleanup when the composable is disposed
                    Glide.with(context).clear(view)
                }
            }

            // Wrap the ImageView with Compose's View composable
            AndroidView(factory = { view })
        }



}