package com.example.navbar.Football.presentation.AddPlayerComponent

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Player
import com.example.navbar.Football.domain.useCase.WrapperCaseClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlayerViewModel @Inject constructor(
    val useCaseClass: WrapperCaseClass
) : ViewModel() {


    private val _addPlayer = mutableStateOf(AddPlayerState())
    val addPlayer = _addPlayer

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun updatePlayerData(player:Player){
        _addPlayer.value = addPlayer.value.copy(
            Name = player.Name,
            Position = player.Position,
            TeamId = player.TeamId,
            Paymnet = player.Payment?:"0",
            DocumentId = player.DocId


        )
    }




    fun eventListener(event: InputEvent) {

        when (event) {
            is InputEvent.EnteredName -> {
                _addPlayer.value = addPlayer.value.copy(
                    Name = event.value
                )
            }

            is InputEvent.EnteredPosition -> {
                _addPlayer.value = addPlayer.value.copy(
                    Position = event.value
                )
            }

            is InputEvent.EnteredTeam -> {
                _addPlayer.value = addPlayer.value.copy(
                    TeamId = event.value
                )
            }

            InputEvent.addEvent -> {

                submit()
            }

            is InputEvent.EnterPayment -> {
                _addPlayer.value = addPlayer.value.copy(
                    Paymnet = event.value
                )
            }

            InputEvent.updateEvent ->{
                update()
            }
        }
    }

    fun update(){

        viewModelScope.launch {

            useCaseClass.updatePlayer.invoke(
                Player(
                    TeamId = _addPlayer.value.TeamId,
                    Position = _addPlayer.value.Position,
                    Name = _addPlayer.value.Name,
                    Resposibility = "",
                    Payment =  _addPlayer.value.Paymnet,
                    DocId = _addPlayer.value.DocumentId

                )
            ).onEach {
                when(it){
                    is Resource.Error ->{}
                    is Resource.Loading ->{
                        _addPlayer.value = addPlayer.value.copy(
                            Loading = true
                        )
                    }
                    is Resource.Success -> {
                        _addPlayer.value = addPlayer.value.copy(
                            Loading = false,
                            TeamId = "",
                            Position = "",
                            Name = "",
                            Resposibility = "",
                            Paymnet = ""
                        )
                        _eventFlow.emit(UiEvent.eventSuccess)
                    }
                }
            }.launchIn(this)
        }


    }

    fun submit() {
        val name = useCaseClass.addPlayerValidationUseCase.execute(addPlayer.value.Name)
        val position =
            useCaseClass.addPlayerValidationUseCase.execute(addPlayer.value.Position ?: "")
        val payment = useCaseClass.addPlayerValidationUseCase.execute(addPlayer.value.Paymnet)

        val hasError = listOf(
            name,
            position,
            payment
        ).any { !it.Success }

        if (hasError) {
            _addPlayer.value = addPlayer.value.copy(
                NameError = "Cant leave empty",
                PositionError = "Cant leave empty",
                TeamIdError = "Cant leave empty",
                PaymnetError = "Enter Amount"
            )
            return
        } else {
            viewModelScope.launch {
                useCaseClass.addPlayer.invoke(
                    Player(
                        TeamId = _addPlayer.value.TeamId,
                        Position = _addPlayer.value.Position,
                        Name = _addPlayer.value.Name,
                        Resposibility = "",
                        Payment =  _addPlayer.value.Paymnet,
                        DocId = null

                    )
                ).onEach {

                    when (it) {
                        is Resource.Error -> {}
                        is Resource.Loading -> {
                            _addPlayer.value = addPlayer.value.copy(
                                Loading = true
                            )
                        }

                        is Resource.Success -> {
                            _addPlayer.value = addPlayer.value.copy(
                                Loading = false,
                                TeamId = "",
                                Position = "",
                                Name = "",
                                Resposibility = "",
                                Paymnet = ""
                            )

                        }
                    }

                }.launchIn(this)
            }
        }


    }
}