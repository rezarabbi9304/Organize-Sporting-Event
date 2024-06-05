package com.example.navbar.Football.presentation.AccountingComponent

import com.example.navbar.Football.domain.model.Expense
import com.example.navbar.Football.domain.model.Player

data class ExpanseState(
    val expanse:List<Expense> = emptyList(),
    val loading:Boolean = false

    )