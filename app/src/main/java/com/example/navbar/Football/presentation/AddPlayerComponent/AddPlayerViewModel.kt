package com.example.navbar.Football.presentation.AddPlayerComponent

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPlayerViewModel @Inject constructor():ViewModel() {


    private val _addPlayer = mutableStateOf(AddPlayerState())
    val addPlayer = _addPlayer


}