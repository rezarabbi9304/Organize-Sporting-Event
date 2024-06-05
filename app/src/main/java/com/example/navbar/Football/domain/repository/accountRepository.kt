package com.example.navbar.Football.domain.repository

import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Expense
import com.example.navbar.Football.domain.model.Player
import com.example.navbar.Football.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface accountRepository {

    suspend fun getExpenses():Flow<Resource<List<Expense>>>



}