package com.example.navbar.Football.presentation.AccountingComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.navbar.Football.domain.useCase.AddPlayerValidationUseCase
import com.example.navbar.Football.domain.useCase.WrapperCaseClass
import com.example.navbar.Football.presentation.PlayerComponent.PlayerViewModel
import com.example.navbar.R

@Composable
fun AccountingScreen(
    viewModel: AccountingViewModel = hiltViewModel(),

    ) {





    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ac_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    radiusX = 5.dp,
                    radiusY = 5.dp,
                ),
            contentScale = ContentScale.FillHeight,

            )

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4A744F).copy(alpha = 0.5f))
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Collection",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal
                    )
                )
                Text(
                    text = "Total = ${viewModel.collection.value} ৳",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF4A744F).copy(alpha = 0.5f))
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Expense",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontStyle = FontStyle.Normal
                            )

                        )
                        Text(
                            text = "Total = ${viewModel.totalExp.value}  ৳",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontStyle = FontStyle.Normal
                            )
                        )
                    }


                }
                items(viewModel.expanseState.value.expanse) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF639168).copy(alpha = 0.2f))
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = it.ExpensHead,
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
                            )
                            Text(
                                text = it.ExpensAmount + "৳",
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }

                item {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF2A522F).copy(alpha = 0.7f))

                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Balance",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontStyle = FontStyle.Normal
                            )

                        )
                        Text(
                            text = "Total = ${viewModel.collection.value.toInt() - viewModel.totalExp.value.toInt()} ৳",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)

                        )
                    }


                }


            }
        }

    }
}