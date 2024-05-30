package com.example.navbar.Football.presentation

import com.example.navbar.Football.domain.model.Team

data class TeamState (
    val teams: List<Team> = emptyList(),

)