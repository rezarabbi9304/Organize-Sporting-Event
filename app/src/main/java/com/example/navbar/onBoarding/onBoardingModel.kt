package com.example.navbar.onBoarding

import android.media.Image
import androidx.annotation.DrawableRes
import com.example.navbar.R
import com.example.navbar.util.onBoardingUtils

sealed class onBoardingModel (
   @DrawableRes val Image:Int,
    val Title:String,
    val Description:String
){
    data object FirstPage:onBoardingModel(
        Image = R.drawable.first_page,
        Title = "Create Your Dream Team",
        Description = "A good Team is all you ever need"
    )
    data object SecondPage:onBoardingModel(
        Image = R.drawable.second_page,
        Title = "Add Your Team Mate",
        Description = "Gather around your trusted warrior"
    )

    data object ThirdPage:onBoardingModel(
        Image =R.drawable.third_page,
        Title = "Ready For some action",
        Description = "Play fifa smoke Shisha"
    )
}

