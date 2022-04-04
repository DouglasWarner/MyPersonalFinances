package com.douglas.mypersonalfinances.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.douglas.mypersonalfinances.R
import com.douglas.mypersonalfinances.data.model.UserApp
import com.douglas.mypersonalfinances.databinding.ActivityLoginBinding
import com.douglas.mypersonalfinances.utils.afterTextChanged
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    //region VARIABLES

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    //endregion

    //region LIFECYCLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityLoginBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        loginViewModel.loginForm.observe(this) { loginState ->
            loginState?.let { state ->
                // disable login button unless both username / password is valid
                binding.login.isEnabled = state.isDataValid

                state.usernameError?.let { error ->
                    binding.username.error = getString(error)
                }
                state.passwordError?.let { error ->
                    binding.password.error = getString(error)
                }
            }
        }

        loginViewModel.loginResult.observe(this) { loginResult ->
            loginResult?.let { result ->

                binding.loading.visibility = View.GONE
                result.error?.let { error ->
                    showLoginFailed(error)
                }
                result.success?.let { success ->
                    updateUiWithUser(success)
                    //Complete and destroy login activity once successful
                    //TODO go to the app and finish the login activity
//                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }

        binding.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        runBlocking {
                            loginViewModel.login(
                                binding.username.text.toString(),
                                binding.password.text.toString()
                            )
                        }
                }
                false
            }
        }

        binding.login.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            runBlocking {
                loginViewModel.login(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )

            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

//endregion

    //region PRIVATE METHODS

    private fun updateUiWithUser(model: UserApp) {
        val welcome = getString(R.string.welcome)
        val displayName = model.name
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    //endregion

}