package com.example.navbar.Screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.navbar.Football.presentation.FootballViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun HomeScreen(isTrue:Boolean,
               navController: NavController,
               viewModel: FootballViewModel = hiltViewModel()) {

    var horizon  = Alignment.CenterHorizontally
    var vertical: Arrangement.Vertical


    if(isTrue){
        horizon = Alignment.CenterHorizontally
        vertical = Arrangement.Center
    }else{
        horizon =  Alignment.End
        vertical = Arrangement.Bottom
    }
//    val db = Firebase.firestore
//
//    db.collection("Team")
//        .get()
//        .addOnSuccessListener { result ->
//            for (document in result) {
//
//                Log.d("FootballS", "${document.id} => ${document.getString("Poster")}")
//
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.w("FootballF", "Error getting documents.", exception)
//        }

    viewModel.getTeam()
    Row(modifier = Modifier.fillMaxSize(),
       horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){


        for(team in viewModel.state.value.teams){
            Column(
                modifier = Modifier.clickable {
                    navController.navigate(ScreenRoute.Job.route+"?TeamId=${team.TeamId}")
                },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(model =team.Poster , contentDescription = "", modifier = Modifier
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ))

                Text(text = team.Name , style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif))

            }

        }
    }
        
}