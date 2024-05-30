package com.example.navbar.Football.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Team
import com.example.navbar.Football.domain.useCase.GetTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FootballViewModel @Inject constructor(
  val  useCase:GetTeam
) :ViewModel() {

    private val _state = mutableStateOf(TeamState())
    val state = _state


    init {
        getTeam()
    }

    fun getTeam(){
        viewModelScope.launch {
            useCase.invoke().onEach {
                when(it){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _state.value = it.data?.let { it1 ->
                            state.value.copy(
                                teams = it1
                            )
                        }!!
                    }
                }
            }.launchIn(this)
        }

    }

}