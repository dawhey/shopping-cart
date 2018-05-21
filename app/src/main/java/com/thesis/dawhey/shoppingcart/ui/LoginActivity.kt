package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import com.thesis.dawhey.shoppingcart.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : RequestResponseActivity<AuthResponse, AuthRequest, LoginViewModel>(), LifecycleOwner {

    override fun provideLayoutResource(): Int = R.layout.activity_login

    override fun provideToolbarTitle(): String = getString(R.string.log_in)

    override fun provideViewModel(): LoginViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(LoginViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginButton.setOnClickListener {
            viewModel.request = AuthRequest(usernameInput.text.toString(), passwordInput.text.toString())
            viewModel.request()
        }

        if (viewModel.isAuthenticated) startActivity(CartActivity::class.java)
    }

    override fun onSuccess() {
        super.onSuccess()
        startActivity(CartActivity::class.java)
    }

    override fun onApiError() {
        super.onApiError()
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.auth_error), Snackbar.LENGTH_SHORT).show()
    }
}
