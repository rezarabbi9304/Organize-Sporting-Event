package com.example.navbar.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navbar.Screen.ScreenRoute

@Composable fun  BottomNavBar(
  navController: NavController
) {
    val screen = listOf(
        ScreenRoute.Home,
        ScreenRoute.Job,
        ScreenRoute.AddNew,

        )

    Row (modifier = Modifier.fillMaxSize(.8f),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically){
        screen.forEach {


            Spacer(modifier = Modifier.width(20.dp))
            MenuItem(menu = it , onClick = {
                Log.d("BottomNavBar", "BottomNavBar: " + it)
                navController.navigate(it){
                    popUpTo(ScreenRoute.Home.route)
                    launchSingleTop = true
                }
            })
        }
    }
}