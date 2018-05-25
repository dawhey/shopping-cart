package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.viewmodels.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : RequestResponseActivity<BindToCartResponse, BindToCartRequest, CartViewModel>() {

    override fun provideViewModel() = ViewModelProvider.AndroidViewModelFactory(application).create(CartViewModel::class.java)

    override fun provideToolbarTitle() = "Select shopping cart"

    override fun provideLayoutResource() = R.layout.activity_cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        buttonNext.setOnClickListener {
            viewModel.request = BindToCartRequest(prefs.token, inputCartId.text.toString())
            viewModel.request() }

        if (viewModel.isCartAssigned) startActivity(ShoppingActivity::class.java)
    }

    override fun onApiError() {
        super.onApiError()
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.invalid_cart_id), Snackbar.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        super.onSuccess()
        prefs.cartId = inputCartId.text.toString()
        startActivity(ShoppingActivity::class.java)
    }
}
