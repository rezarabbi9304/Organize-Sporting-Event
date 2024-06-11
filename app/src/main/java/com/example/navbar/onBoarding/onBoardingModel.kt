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
        Image = R.drawable.first_page_image,
        Title = "Create The Ultimate Team",
        Description = "we can achieve anything with Team Work"
    )
    data object SecondPage:onBoardingModel(
        Image = 0,
        Title = "Add Your Team Mate",
        Description = "we can achieve anything with Team Work"
    )

    data object ThirdPage:onBoardingModel(
        Image = 0,
        Title = "Ready For some action",
        Description = "we can achieve anything with Team Work"
    )
}

