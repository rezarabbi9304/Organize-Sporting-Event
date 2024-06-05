package com.example.navbar.Football.presentation.PlayerComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navbar.Football.domain.model.Player

@Composable
fun PlayerItem(
    player: Player,
    onClick: (Player) -> Unit

) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(player) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF375E3C).copy(alpha = 0.8f)
        )) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))

                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = player.Name,
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium , fontFamily = FontFamily.SansSerif)
                )
                player.Position?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal,fontFamily = FontFamily.SansSerif)
                    )
                }

            }
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                player.Resposibility?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium,fontFamily = FontFamily.SansSerif)
                    )
                }
                player.Payment?.let {
                        Text(
                            text = "${ player.Payment}",
                            color = Color.White,
                            style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium,fontFamily = FontFamily.SansSerif)
                        )


                }


            }

        }
    }
}