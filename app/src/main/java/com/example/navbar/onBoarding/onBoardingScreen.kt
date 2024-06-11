package com.example.navbar.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onBoardingScreen(onFinish:()->Unit) {


    val pages = listOf(
        onBoardingModel.FirstPage,
        onBoardingModel.SecondPage,
        onBoardingModel.ThirdPage
    )

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
                ){
                Button(onClick = {
                    if(pagerState.currentPage >0){
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            
                        }
                    }
                }) {
                Text(text = "Back")
                }

                Button(onClick = {
                    if(pagerState.currentPage <= pages.size){
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)

                        }
                    }
                }) {
                    Text(text = "Next")
                }
            }
        }
    ) {
        Column (modifier = Modifier
            .padding(it),
            ){



          HorizontalPager(state = pagerState) {index->

              Column(modifier = Modifier.fillMaxSize(),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center) {

                  Text(text =  pages.get(index).Title)
              }
          }
        }
    }


}