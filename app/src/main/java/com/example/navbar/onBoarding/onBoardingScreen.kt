package com.example.navbar.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navbar.R
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

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Start")
                else -> listOf("", "")
            }
        }
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
                ){

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center){
                    Button(onClick = {
                        if(pagerState.currentPage >0){
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)

                            }
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )) {
                        Text(text = buttonState.value[0] , color = Color.Gray)
                    }
                }

                Box(modifier = Modifier.weight(1f)){
                    IndicatorUI(pageSize = pages.size, currentPage =pagerState.currentPage )
                }
                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center){
                    Button(onClick = {
                        if(pagerState.currentPage < pages.size-1){
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)

                            }
                        }else{
                            onFinish()
                        }
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )) {
                        Text(text = buttonState.value[1])
                    }
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


                  Box(modifier = Modifier.fillMaxHeight(.5f)){


                      Image(
                          painter = painterResource(R.drawable.blob),
                          contentDescription = null,

                          modifier = Modifier
                              .fillMaxHeight(1f)
                              .background(Color.Transparent)
                          ,
                          contentScale = ContentScale.FillHeight,

                          )
                      Image(
                          painter = painterResource(pages.get(index).Image),
                          contentDescription = null,

                          modifier = Modifier
                              .fillMaxHeight(1f)
                              .background(Color.Transparent)
                          ,
                          contentScale = ContentScale.FillHeight,

                          )
                  }


                  Text(text =  pages.get(index).Title , style = TextStyle(fontSize = 30.sp, fontStyle = FontStyle.Italic , fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(20.dp))
                  Text( modifier = Modifier.padding(10.dp),text =  pages.get(index).Description, textAlign = TextAlign.Center , style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Italic))
              }
          }
        }
    }


}