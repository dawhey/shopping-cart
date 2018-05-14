package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thesis.dawhey.shoppingcart.BR
import com.thesis.dawhey.shoppingcart.R

class LoginActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(LoginViewModel::class.java)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.setVariable(BR.vm, viewModel)
    }
}
