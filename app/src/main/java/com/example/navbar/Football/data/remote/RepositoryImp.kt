package com.example.navbar.Football.data.remote

import android.util.Log
import coil.network.HttpException
import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Player
import com.example.navbar.Football.domain.model.Team
import com.example.navbar.Football.domain.repository.footballRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okio.IOException
import java.util.jar.Attributes.Name

class RepositoryImp(
    val db: FirebaseFirestore
):footballRepository {
    override suspend fun getTeam(): Flow<Resource<List<Team>>> = flow {
       emit(Resource.Loading())

        try {
            val data  = db.collection("Team")
                .get()
                .await().documents.map {

                        Team(
                            TeamId = it.getString("TeamId")?:"",
                            Poster = it.getString("Poster")!!,
                            Name = it.getString("Name")!!
                        )

                }
            emit(Resource.Success(data))


        }catch (Ex:HttpException){
            emit(Resource.Error(
                message = "Something Went Wrong"
            ))

        }catch (Ex:IOException){
            emit(Resource.Error(
                message = "No Internet Connection"
            ))
        }
    }

    override suspend fun getPlayer(
        teamId:String
    ): Flow<Resource<List<Player>>> = flow{
        emit(Resource.Loading())
        try {
            val data  = db.collection("Player")
                .whereEqualTo("TeamId","0${teamId}")
                .get()
                .await().documents.map {
                    Player(
                        TeamId = it.getString("TeamId")?:"-1",
                        Name = it.getString("Name")?:"Unknown",
                        Resposibility = it.getString("Responsibility"),
                        Position = it.getString("Position")
                    )
                }

            emit(Resource.Success(data))

        }catch (Ex:HttpException){
            emit(Resource.Error(
                message = "Something Went Wrong"
            ))

        }catch (Ex:IOException){
            emit(Resource.Error(
                message = "No Internet Connection"
            ))
        }
    }
}