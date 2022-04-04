package com.douglas.mypersonalfinances.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas.mypersonalfinances.R
import com.douglas.mypersonalfinances.data.repository.UserAppRepository

class LoginViewModel(private val repository: UserAppRepository) : ViewModel() {

    private val loginFormState = MutableLiveData<LoginFormState>()
    private val loginFormResult = MutableLiveData<LoginResult>()

    //region OBSERVABLES

    val loginForm: LiveData<LoginFormState> = loginFormState
    val loginResult: LiveData<LoginResult> = loginFormResult

    //endregion

    //region PUBLIC FUNCTION

    suspend fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        repository.userApp.collect { users ->
            users.find {
                it.userAccount == username && it.password == password
            }?.let { userApp ->
                loginFormResult.postValue(LoginResult(success = userApp, error = null))
                return@collect
            }
            loginFormResult.postValue(LoginResult(success = null, error = R.string.errorDefault))
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            loginFormState.postValue(LoginFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            loginFormState.postValue(LoginFormState(passwordError = R.string.invalid_password))
        } else {
            loginFormState.postValue(LoginFormState(isDataValid = true))
        }
    }

    //endregion

    //region PRIVATE FUNCTION

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    //endregion
}