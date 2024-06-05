package com.example.navbar.Football.di

import com.example.navbar.Football.data.remote.AccRepositoryImp
import com.example.navbar.Football.data.remote.RepositoryImp
import com.example.navbar.Football.domain.repository.accountRepository
import com.example.navbar.Football.domain.repository.footballRepository
import com.example.navbar.Football.domain.useCase.AddPlayer
import com.example.navbar.Football.domain.useCase.AddPlayerValidationUseCase
import com.example.navbar.Football.domain.useCase.GetExpenseCase
import com.example.navbar.Football.domain.useCase.GetPlayer
import com.example.navbar.Football.domain.useCase.GetTeam
import com.example.navbar.Football.domain.useCase.UpdatePlayer
import com.example.navbar.Football.domain.useCase.WrapperCaseClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideFirebaseInstance():FirebaseFirestore{
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideRepositoryImp(fs:FirebaseFirestore):footballRepository{
        return RepositoryImp(fs)
    }

    @Provides
    @Singleton
    fun provideAccRepositoryImp(fs:FirebaseFirestore):accountRepository{
        return AccRepositoryImp(fs)
    }

//    @Provides
//    @Singleton
//    fun provideUseCaseGetTeam(repo:footballRepository):GetTeam{
//        return GetTeam(repo)
//    }
//    @Provides
//    @Singleton
//    fun provideUseCaseGetPlayer(repo:footballRepository):GetPlayer{
//        return GetPlayer(repo)
//    }

    @Provides
    @Singleton
    fun provideUseCaseForAll(repo:footballRepository,accRepo:accountRepository):WrapperCaseClass{
        return WrapperCaseClass(
            getTeam = GetTeam(repo),
            getPlayer = GetPlayer(repo),
            addPlayerValidationUseCase = AddPlayerValidationUseCase(),
            addPlayer = AddPlayer(repo),
            updatePlayer = UpdatePlayer(repo),
            getExpenseCase = GetExpenseCase(accRepo)
        )
    }

}