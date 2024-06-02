package com.example.navbar.Football.presentation.PlayerComponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dentonstudio.rickandmorty.util.GifScreen
import com.example.navbar.Football.presentation.PlayerComponent.PlayerItem
import com.example.navbar.Football.presentation.PlayerComponent.PlayerViewModel
import com.example.navbar.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerScreen(
    navController : NavController,
    viewModel:PlayerViewModel = hiltViewModel()
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.Start,
      ){

        val player = viewModel.playerState.value.player

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary)) {
            Image(
                painter = painterResource(R.drawable.bg_field),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillHeight,

                )

            LazyColumn(
                modifier = Modifier

                    .background(
                        color = Color.Transparent
                    )
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                stickyHeader {    AsyncImage(model =viewModel.TeamPoster.value , contentDescription = "", modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )) }
                item { if(viewModel.playerState.value.loading){

                    GifScreen()
                } }
                items(player.size){
                    PlayerItem(player.get(it))
                }
            }
        }



    }
        
}