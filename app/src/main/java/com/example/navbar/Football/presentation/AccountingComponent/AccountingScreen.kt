package com.example.navbar.Football.presentation.AccountingComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.navbar.Football.domain.useCase.AddPlayerValidationUseCase
import com.example.navbar.Football.domain.useCase.WrapperCaseClass
import com.example.navbar.Football.presentation.PlayerComponent.PlayerViewModel
import com.example.navbar.R

@Composable
fun AccountingScreen(
    viewModel: PlayerViewModel = hiltViewModel(),

) {


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ac_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight,

            )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Collection",
                        color = Color.Gray,
                        style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Normal)
                    )
                    Text(text = "Total Collection")
                }
            }
            item {



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Expense",
                            color = Color.Gray,
                            style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Normal)
                        )
                        Text(text = "Total Expense")
                    }


            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Due",
                        color = Color.Gray,
                        style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Normal)
                    )
                    Text(text = "Total Due")
                }
            }
        }
    }
}