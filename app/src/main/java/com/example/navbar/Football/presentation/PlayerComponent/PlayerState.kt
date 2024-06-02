package com.example.navbar.Football.presentation.PlayerComponent

import com.example.navbar.Football.domain.model.Player

data class PlayerState(
    val player:List<Player> = emptyList(),
    val loading:Boolean = false

    )