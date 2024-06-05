package com.example.navbar.Football.presentation.AccountingComponent

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentonstudio.rickandmorty.util.Resource
import com.example.navbar.Football.domain.model.Expense
import com.example.navbar.Football.domain.useCase.WrapperCaseClass
import com.example.navbar.Football.presentation.PlayerComponent.PlayerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountingViewModel @Inject constructor(
    val usecase: WrapperCaseClass
) : ViewModel() {

    private val _collection = mutableStateOf(0)
    val collection = _collection

    private val _totalExp = mutableStateOf(0)
    val totalExp = _totalExp

    val data = collection.value

    private val _playerState = mutableStateOf(PlayerState())
    val playerState = _playerState

    private val _expanseState = mutableStateOf(ExpanseState())
    val expanseState = _expanseState

    init {

        getAllPlayerCollection()


        getAllExpenses()
    }

    private fun getAllExpenses() {
        viewModelScope.launch {
            usecase.getExpenseCase.invoke().onEach {

                when(it){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _expanseState.value = expanseState.value.copy(
                            expanse = it.data!!,
                            loading = false
                        )
                        for(exp in it.data){
                            _totalExp.value +=exp.ExpensAmount.toInt()
                        }

                    }
                }

            }.launchIn(this)
        }
    }



    private fun getAllPlayerCollection() {
        viewModelScope.launch {
            usecase.getPlayer.invokeAll().onEach {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _playerState.value = playerState.value.copy(
                            player = it.data!!,
                            loading = false
                        )

                        processCollection()
                    }
                }

            }.launchIn(this)
        }
    }

    private fun processCollection() {
        var count = 0

        for (player in _playerState.value.player) {
            count += player.Payment?.toInt() ?: 0

        }

        _collection.value = count

    }
}