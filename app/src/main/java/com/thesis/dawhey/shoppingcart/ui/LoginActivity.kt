package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.viewmodels.LoginViewModel
import com.thesis.dawhey.shoppingcart.viewmodels.ViewStatus
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = getString(R.string.log_in)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(LoginViewModel::class.java)
        addViewStatusObserver()
        loginButton.setOnClickListener { viewModel.authenticate(User(usernameInput.text.toString(), passwordInput.text.toString())) }

        if (viewModel.isAuthenticated) { startMainActivity() }
    }



    fun addViewStatusObserver() {
        viewModel.viewStatus.observe(
                this, Observer {status: ViewStatus? ->
                    when (status) {
                        ViewStatus.LOADING -> progressBar.visibility = View.VISIBLE
                        ViewStatus.ERROR -> {
                            Snackbar.make(findViewById(android.R.id.content), "Authentication error", Snackbar.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE
                        }
                        ViewStatus.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            startMainActivity()
                        }
                    }
                }
        )
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
