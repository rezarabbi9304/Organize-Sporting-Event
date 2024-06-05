package com.example.navbar.Football.data.remote

import coil.network.HttpException
import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Expense
import com.example.navbar.Football.domain.repository.accountRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException

class AccRepositoryImp(
    val db: FirebaseFirestore
): accountRepository {

    override suspend fun getExpenses(): Flow<Resource<List<Expense>>> = flow{
    emit(Resource.Loading())

        try {
            val data  = db.collection("Expense")
                .get()
                .await().documents.map {

                    Expense(
                        ExpenseId = it.id,
                        ExpensAmount = it.getString("ExpensAmount").toString()!!,
                        ExpensHead = it.getString("ExpensHead")!!
                    )

                }
            emit(Resource.Success(data))


        }catch (Ex: HttpException){
            emit(Resource.Error(
                message = "Something Went Wrong"
            ))

        }catch (Ex: IOException){
            emit(Resource.Error(
                message = "No Internet Connection"
            ))
        }

    }
}