package com.example.navbar.Football.presentation.AddPlayerComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navbar.R

@Composable
fun AddScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.onPrimary)) {
        Image(
            painter = painterResource(R.drawable.grass_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight,

            )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
          TextField(value = "", onValueChange = {}, label = { Text(text = "Name")})
            TextField(value = "", onValueChange = {}, label = { Text(text = "Position")})
            TextField(value = "", onValueChange = {}, label = { Text(text = "Select Team")})
        }
    }


        
}