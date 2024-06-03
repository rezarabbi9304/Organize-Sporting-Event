package com.example.navbar.Football.presentation.PlayerComponent

import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.dentonstudio.rickandmorty.util.Resource

import com.example.navbar.Football.domain.useCase.GetPlayer
import com.example.navbar.Football.domain.useCase.WrapperCaseClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val  useCase: WrapperCaseClass,
    savedStateHandle: SavedStateHandle,
):ViewModel() {

    private val _playerState = mutableStateOf(PlayerState())
    val playerState = _playerState
    private val _TeamPoster = mutableStateOf("")
    val TeamPoster =_TeamPoster


    init {
        savedStateHandle.get<Int>("TeamId")?.let {
            if(it!=-1){
                getAllPlayer(it.toString())
            }
        }

        savedStateHandle.get<String>("poster")?.let {
            _TeamPoster.value = it
        }
    }

    fun getAllPlayer(teamId: String) {

        viewModelScope.launch {
            useCase.getPlayer.invoke(teamId).onEach {
                when(it){
                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        _playerState.value=  playerState.value.copy(
                            loading = true
                        )
                    }

                    is Resource.Success -> {

                        _playerState.value = playerState.value.copy(
                            player = it.data!!,
                            loading = false
                        )
                    }
                }

            }.launchIn(this)
        }

    }

}