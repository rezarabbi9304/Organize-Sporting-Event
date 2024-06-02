package com.example.navbar.Football.presentation.AddPlayerComponent


data class AddPlayerState(
    val TeamId:String = "",
    val TeamIdError:String? =null,
    val Name:String = "",
    val NameError:String?=null,
    val Resposibility:String? = null,
    val Position:String? = null,
    val PositionError:String =""
)