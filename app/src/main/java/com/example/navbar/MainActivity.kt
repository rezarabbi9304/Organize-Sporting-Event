package com.example.navbar


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navbar.Football.presentation.AccountingComponent.AccountingScreen
import com.example.navbar.Football.presentation.AddPlayerComponent.AddScreen
import com.example.navbar.Screen.HomeScreen
import com.example.navbar.Football.presentation.PlayerComponent.PlayerScreen
import com.example.navbar.Screen.ScreenRoute
import com.example.navbar.component.BottomNavBar
import com.example.navbar.onBoarding.onBoardingScreen
import com.example.navbar.ui.theme.NavBarTheme
import com.example.navbar.util.onBoardingUtils
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private  val onBoarding by lazy { onBoardingUtils(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val activity = this as Activity


        setContent {
            NavBarTheme {
                val navController = rememberNavController()
                var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                BackHandler(enabled = drawerState.isOpen) {
                    scope.launch {
                        drawerState.apply {
                            close()
                        }
                    }

                }

                ModalNavigationDrawer(drawerState = drawerState,drawerContent = {
                    ModalDrawerSheet {
                      LazyColumn() {
                          item {
                              Icon(imageVector = Icons.Rounded.Home, contentDescription ="Home")
                          }
                          item {
                              Icon(imageVector = Icons.Rounded.Home, contentDescription ="Home")
                          }
                          item {
                              Icon(imageVector = Icons.Rounded.Home, contentDescription ="Home")
                          }

                      }
                    }
                }) {
                    if(onBoarding.isOnBoardingComplete()){
                        onBoardingScreen{
                            println("Finished")
                        }
                    }else{
                        Scaffold(


                            bottomBar = {
                                BottomAppBar( containerColor = Color(0xFF375E3C),actions = {
                                    BottomNavBar(navController = navController)
                                },
                                    floatingActionButton = {
                                        FabPosition.Center
                                        FloatingActionButton(onClick ={
                                            scope.launch {
                                                drawerState.apply {
                                                    if (isClosed) open() else close()
                                                }
                                            }
                                        }) {
                                            Icon(imageVector = Icons.Default.Add, contentDescription = "add")

                                        }
                                    }

                                )
                            }
                        ) { paddingValues ->
                            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            NavHost(  modifier = Modifier.padding(paddingValues),navController = navController, startDestination = ScreenRoute.Home.route){

                                composable(route = ScreenRoute.Home.route){
                                    HomeScreen(true, navController = navController)
                                }
                                composable(route = ScreenRoute.Job.route+ "?TeamId={TeamId}"+"?poster={poster}",
                                    arguments = listOf(
                                        navArgument(
                                            name = "TeamId",
                                        ){
                                            type = NavType.IntType
                                            defaultValue = -1
                                        },
                                        navArgument(
                                            name = "poster",
                                        ){
                                            type = NavType.StringType
                                            defaultValue = ""
                                        }
                                    )
                                ){
                                    PlayerScreen(
                                        navController = navController
                                    )
                                }
                                composable(route = ScreenRoute.AddNew.route){
                                    AddScreen()
                                }

                                composable(route = ScreenRoute.Account.route){
                                    AccountingScreen()
                                }
                            }


                        }
                    }



                }

            }
        }
    }
}








@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavBarTheme {
        Greeting("Android")
    }
}