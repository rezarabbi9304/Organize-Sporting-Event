package com.example.navbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navbar.Screen.AddScreen
import com.example.navbar.Screen.HomeScreen
import com.example.navbar.Screen.JobScreen
import com.example.navbar.Screen.ScreenRoute
import com.example.navbar.component.BottomNavBar
import com.example.navbar.ui.theme.NavBarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Scaffold(


                            bottomBar = {
                                BottomAppBar( actions = {
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
                            NavHost(  modifier = Modifier.padding(paddingValues),navController = navController, startDestination = ScreenRoute.Home.route){

                                composable(route = ScreenRoute.Home.route){
                                    HomeScreen(true)
                                }
                                composable(route = ScreenRoute.Job.route){
                                    JobScreen()
                                }
                                composable(route = ScreenRoute.AddNew.route){
                                    AddScreen()
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