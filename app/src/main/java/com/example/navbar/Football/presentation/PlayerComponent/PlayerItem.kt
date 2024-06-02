package com.example.navbar.Football.presentation.PlayerComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navbar.Football.domain.model.Player

@Composable
fun PlayerItem(
     player: Player
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Column {
                Text(text = player.Name, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium))
                player.Position?.let { Text(text = it, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal)) }

            }
            Column {
                player.Resposibility?.let { Text(text = it, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)) }


            }

        }
    }
}