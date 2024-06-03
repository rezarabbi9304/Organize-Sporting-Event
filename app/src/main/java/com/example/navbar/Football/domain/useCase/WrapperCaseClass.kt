package com.example.navbar.Football.domain.useCase

data class WrapperCaseClass(
    val getPlayer: GetPlayer,
    val getTeam: GetTeam,
    val addPlayerValidationUseCase: AddPlayerValidationUseCase,
    val addPlayer: AddPlayer

)