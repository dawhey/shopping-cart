package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.viewmodels.CartViewModel
class CartActivity : ViewStatusActivity<BindToCartResponse, BindToCartRequest, CartViewModel>() {

    override fun provideViewModel(): CartViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CartViewModel::class.java)

    override fun provideToolbarTitle(): String = "Select shopping cart"

    override fun provideLayoutResource(): Int = R.layout.activity_cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }
}
