package com.douglas.mypersonalfinances.ui.login

import com.douglas.mypersonalfinances.data.model.UserApp

data class LoginResult(
    val error: Int? = null,
    val success: UserApp? = null
)
