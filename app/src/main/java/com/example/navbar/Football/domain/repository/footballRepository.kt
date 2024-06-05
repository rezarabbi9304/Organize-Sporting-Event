package com.example.navbar.Football.domain.repository

import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Player
import com.example.navbar.Football.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface footballRepository {

    suspend fun getTeam():Flow<Resource<List<Team>>>
    suspend fun getPlayer(teamId:String):Flow<Resource<List<Player>>>

    suspend fun addPlayer(player: Player):Flow<Resource<String>>
    suspend fun update(player: Player):Flow<Resource<String>>
    suspend fun getAllPlayer():Flow<Resource<List<Player>>>
}