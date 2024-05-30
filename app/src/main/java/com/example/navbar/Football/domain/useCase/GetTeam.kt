package com.example.navbar.Football.domain.useCase

import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Team
import com.example.navbar.Football.domain.repository.footballRepository
import kotlinx.coroutines.flow.Flow

class GetTeam(
    val repo:footballRepository
) {

    suspend fun invoke():Flow<Resource<List<Team>>>{
        return repo.getTeam()
    }
}