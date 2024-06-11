package com.example.navbar.Football.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.dentonstudio.rickandmorty.util.GifScreen
import com.example.navbar.Football.presentation.AddPlayerComponent.AddPlayerViewModel
import com.example.navbar.Football.presentation.AddPlayerComponent.InputEvent


@Composable
fun CustomPlayerUpdateDialog (
    viewModel: AddPlayerViewModel = hiltViewModel(),
    onDismissed:()->Unit,
    onConfrimed:()->Unit,
){

    Dialog(onDismissRequest = { onDismissed},
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )


    ) {

        Card(

            modifier = Modifier
                .fillMaxWidth(0.95f)

                .padding(5.dp),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                if (viewModel.addPlayer.value.Loading) {
                    GifScreen()
                } else {
                    TextField(
                        value = viewModel.addPlayer.value.Name,
                        onValueChange = { viewModel.eventListener(InputEvent.EnteredName(it)) },
                        label = { Text(text = "Name") },
                        enabled = false)
                    viewModel.addPlayer.value.NameError?.let {
                        Text(
                            text = it,
                            style = TextStyle(color = Color.White)
                        )
                    }

                    TextField(
                        value = viewModel.addPlayer.value.Position ?: "",
                        onValueChange = { viewModel.eventListener(InputEvent.EnteredPosition(it)) },
                        label = { Text(text = "Position") },
                        )
                    viewModel.addPlayer.value.PositionError?.let {

                        Text(text = it, style = TextStyle(color = Color.White))

                    }

                    TextField(
                        value = viewModel.addPlayer.value.TeamId,
                        onValueChange = { viewModel.eventListener(InputEvent.EnteredTeam(it)) },
                        label = { Text(text = "Team") },
                        enabled = false)
                    viewModel.addPlayer.value.TeamIdError?.let {
                        Text(
                            text = it,
                            style = TextStyle(color = Color.White)
                        )
                    }

                    TextField(
                        value = viewModel.addPlayer.value.Paymnet,
                        onValueChange = { viewModel.eventListener(InputEvent.EnterPayment(it)) },
                        label = { Text(text = "Payment") })
                    viewModel.addPlayer.value.PaymnetError?.let {
                        Text(
                            text = it,
                            style = TextStyle(color = Color.White)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.Absolute.SpaceAround) {
                        Button(
                            onClick = { viewModel.eventListener(InputEvent.updateEvent) }, modifier = Modifier

                                .height(50.dp)


                            ) {
                            Text(text = "Update Player")
                        }

                        Button(
                            onClick = { onDismissed() }, modifier = Modifier

                                .height(50.dp)
                                ,

                            ) {
                            Text(text = "Cancel")
                        }
                    }

                }


            }
        }

    }

}