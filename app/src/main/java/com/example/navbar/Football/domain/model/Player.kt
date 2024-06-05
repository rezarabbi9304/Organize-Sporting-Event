package com.example.navbar.Football.domain.model

data class Player(
   val TeamId:String = "",
    val Name:String = "",
    val Resposibility:String? = null,
    val Position:String?=null,
    val Payment:String?= null,
    val DocId:String?=null
)