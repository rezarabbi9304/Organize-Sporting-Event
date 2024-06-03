package com.example.navbar.Football.presentation.AddPlayerComponent

sealed class InputEvent(){
    data class EnteredName(val value: String):InputEvent()
    data class EnteredPosition(val value: String):InputEvent()
    data class EnteredTeam(val value: String):InputEvent()
    data class EnterPayment(val value: String):InputEvent()

    object  addEvent:InputEvent()
}