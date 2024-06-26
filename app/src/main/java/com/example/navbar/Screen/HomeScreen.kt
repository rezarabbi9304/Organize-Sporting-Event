package com.example.navbar.Screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dentonstudio.rickandmorty.util.GifScreen
import com.example.navbar.Football.presentation.CustomPlayerUpdateDialog
import com.example.navbar.Football.presentation.FootballViewModel
import com.example.navbar.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun HomeScreen(isTrue:Boolean,
               navController: NavController,
               viewModel: FootballViewModel = hiltViewModel()) {

    viewModel.getTeam()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.onPrimary)) {
        Image(
            painter = painterResource(R.drawable.ground_fc),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight,

            )
        if(viewModel.state.value.loading){
            GifScreen()
        }



        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "6th June, 8:00 PM", color = Color.White, style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                var count = 0

                for(team in viewModel.state.value.teams){
                    count++
                    Column(
                        modifier = Modifier.clickable {
                            navController.navigate(ScreenRoute.Job.route+"?TeamId=${team.TeamId}"+"?poster=${team.Poster} ")
                        },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(model =team.Poster , contentDescription = "", modifier = Modifier
                            .height(150.dp)
                            .clip(
                                RoundedCornerShape(15.dp)
                            ))

                        Text(text = team.Name , style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif, color = Color.White))

                    }
                    if(count == 1) {
                        Text(
                            text = "VS",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
      

    }

        
}