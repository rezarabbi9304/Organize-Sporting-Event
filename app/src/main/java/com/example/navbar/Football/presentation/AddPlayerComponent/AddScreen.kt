package com.example.navbar.Football.presentation.AddPlayerComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dentonstudio.rickandmorty.util.GifScreen
import com.example.navbar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    viewModel: AddPlayerViewModel = hiltViewModel()
) {

    var isDropDown = remember {
        mutableStateOf(false)
    }

    var selectedValue = remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {
        Image(
            painter = painterResource(R.drawable.grass_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    radiusX = 5.dp,
                    radiusY = 5.dp,
                ),
            contentScale = ContentScale.FillHeight,

            )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            if (viewModel.addPlayer.value.Loading) {
                GifScreen()
            } else {
                TextField(
                    value = viewModel.addPlayer.value.Name,
                    onValueChange = { viewModel.eventListener(InputEvent.EnteredName(it)) },
                    label = { Text(text = "Name") })
                viewModel.addPlayer.value.NameError?.let {
                    Text(
                        text = it,
                        style = TextStyle(color = Color.White)
                    )
                }

                TextField(
                    value = viewModel.addPlayer.value.Position ?: "",
                    onValueChange = { viewModel.eventListener(InputEvent.EnteredPosition(it)) },
                    label = { Text(text = "Position") })
                viewModel.addPlayer.value.PositionError?.let {

                    Text(text = it, style = TextStyle(color = Color.White))

                }


                ExposedDropdownMenuBox(
                expanded = isDropDown.value, onExpandedChange = {
                        println(it)
                        if(isDropDown.value){
                            isDropDown.value = false
                        }else{
                            isDropDown.value=it
                        }
                  },
                    ) {
                    TextField( modifier = Modifier.menuAnchor(), value =selectedValue.value, onValueChange = {},
                        label = { Text(text = "Select Team") },
                     trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded =  isDropDown.value)},
                        readOnly = true)
                    ExposedDropdownMenu(expanded =  isDropDown.value, onDismissRequest = {  isDropDown.value = false}) {

                        for (team in viewModel.state.value.teams){
                            DropdownMenuItem(
                                text = { Text(text = team.Name) }, onClick = {selectedValue.value =team.Name
                                    isDropDown.value = false
                                    viewModel.eventListener(InputEvent.EnteredTeam(team.TeamId))                                           },
                            )
                        }

                    }
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
                Button(
                    onClick = { viewModel.eventListener(InputEvent.addEvent) }, modifier = Modifier

                        .height(50.dp)
                        .fillMaxWidth(.5f),

                ) {
                    Text(text = "Add Player")
                }
            }


        }
    }


}