package com.example.mag.startingScreens.sign_in

import androidx.lifecycle.ViewModel
import com.example.mag.startingScreens.sign_in.SignInResult
import com.example.mag.startingScreens.sign_in.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSighInSuccessful = result.data != null,
            signInError = result.errorMessage

        ) }
    }


    fun resetState(){
        _state.update { SignInState() }
    }
}