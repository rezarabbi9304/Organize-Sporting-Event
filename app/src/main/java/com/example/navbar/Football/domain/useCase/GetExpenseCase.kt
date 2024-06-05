package com.example.navbar.Football.domain.useCase

import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Expense
import com.example.navbar.Football.domain.repository.accountRepository
import kotlinx.coroutines.flow.Flow

class GetExpenseCase (
    val repo:accountRepository
){

    suspend fun invoke(): Flow<Resource<List<Expense>>>{
        return repo.getExpenses()
    }
}