package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thesis.dawhey.shoppingcart.BR
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.databinding.ActivityLoginBinding
import com.thesis.dawhey.shoppingcart.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = getString(R.string.log_in)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(LoginViewModel::class.java)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.setVariable(BR.vm, viewModel)
        binding.setLifecycleOwner(this)

        if (viewModel.isAuthenticated) {

        }
    }
}
