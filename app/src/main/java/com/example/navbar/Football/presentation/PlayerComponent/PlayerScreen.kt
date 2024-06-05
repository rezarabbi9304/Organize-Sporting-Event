package com.example.navbar.Football.presentation.PlayerComponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dentonstudio.rickandmorty.util.GifScreen
import com.example.navbar.Football.domain.model.Player
import com.example.navbar.Football.presentation.AddPlayerComponent.AddPlayerViewModel
import com.example.navbar.Football.presentation.AddPlayerComponent.UiEvent
import com.example.navbar.Football.presentation.CustomPlayerUpdateDialog
import com.example.navbar.Football.presentation.PlayerComponent.PlayerItem
import com.example.navbar.Football.presentation.PlayerComponent.PlayerViewModel
import com.example.navbar.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerScreen(
    navController : NavController,
    viewModel:PlayerViewModel = hiltViewModel(),
    addPlayerViewModel: AddPlayerViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val offset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset.toFloat() } }

    // Max size of the image
    val maxSize = 300.dp
    // Min size of the image
    val minSize = 10.dp
    // Scaling factor to control sensitivity of size change
    val scaleFactor = 1

    // Calculating the size based on the scroll offset
    val imageSize: Dp = (maxSize - (offset / scaleFactor).dp).coerceIn(minSize, maxSize)

    val isDialogShow = remember {
        mutableStateOf(false);
    }

    LaunchedEffect(key1 = true) {
        addPlayerViewModel.eventFlow.collectLatest { event ->
            when (event) {
                UiEvent.eventSuccess -> {
                    isDialogShow.value = false
                    viewModel.getAllPlayer()
                }
            }
        }
    }

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
            
            if(isDialogShow.value){
                CustomPlayerUpdateDialog(onDismissed = {isDialogShow.value = false}) {
                    
                }
            }

            LazyColumn(
                state = listState,
                modifier = Modifier

                    .background(
                        color = Color.Transparent
                    )
                   ,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                item{    AsyncImage(model =viewModel.TeamPoster.value , contentDescription = "", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )) }
                
                stickyHeader { Spacer(modifier = Modifier.height(20.dp)) }
                item { if(viewModel.playerState.value.loading){

                    GifScreen()
                } }
                items(player.sortedBy {
                    it.Name
                }){
                    PlayerItem(it, onClick = {
                       isDialogShow.value = true;
                        addPlayerViewModel.updatePlayerData(it)
                    })
                }
            }
        }



    }
        
}