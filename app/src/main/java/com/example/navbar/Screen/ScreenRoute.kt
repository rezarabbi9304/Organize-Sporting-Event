package com.example.navbar.Screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Vector

sealed class ScreenRoute(
    val route:String,
    val title:String,
    val ImageVector: ImageVector
) {

    object Home: ScreenRoute(
        route = "Home",
        title = "Home",
        ImageVector =  Icons.Rounded.Home
    )

    object Job: ScreenRoute(
        route = "Player",
        title = "Player",
        ImageVector =  Icons.Rounded.AccountCircle
    )

    object AddNew: ScreenRoute(
        route = "AddNew",
        title = "add",
        ImageVector =  Icons.Rounded.Favorite
    )
}