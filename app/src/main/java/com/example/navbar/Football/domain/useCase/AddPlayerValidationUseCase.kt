package com.example.navbar.Football.domain.useCase


class AddPlayerValidationUseCase {


    fun execute(value:String):validationResult{

        if(value.isEmpty()){

            return validationResult(
                Error = "Can not leave empty",
                Success = false
            )
        }

        return validationResult(
            Success = true
        )
    }
}