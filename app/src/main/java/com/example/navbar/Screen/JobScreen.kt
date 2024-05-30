package com.example.navbar.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.navbar.Football.presentation.PlayerComponent.PlayerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.format

@Composable
fun JobScreen(
    navController : NavController,
    viewModel:PlayerViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        for (player in viewModel.playerState.value.player){

            Text(text = player.Name , style = TextStyle(fontSize = 30.sp , fontWeight = FontWeight.Bold) , color = MaterialTheme.colorScheme.inversePrimary)
        }
    }
        
}