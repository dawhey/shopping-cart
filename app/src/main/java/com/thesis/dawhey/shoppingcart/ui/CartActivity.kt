package com.thesis.dawhey.shoppingcart.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.viewmodels.LoginViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }
}
