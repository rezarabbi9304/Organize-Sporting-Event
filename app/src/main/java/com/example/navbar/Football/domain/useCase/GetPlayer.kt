package com.example.navbar.Football.domain.useCase

import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Player
import com.example.navbar.Football.domain.model.Team
import com.example.navbar.Football.domain.repository.footballRepository
import kotlinx.coroutines.flow.Flow

class GetPlayer(
    val repo:footballRepository
) {

    suspend fun invoke(teamId:String):Flow<Resource<List<Player>>>{
        return repo.getPlayer(teamId)
    }
}