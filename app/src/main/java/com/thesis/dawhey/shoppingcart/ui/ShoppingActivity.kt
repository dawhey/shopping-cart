package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import com.thesis.dawhey.shoppingcart.viewmodels.ShoppingViewModel

class ShoppingActivity : RequestResponseActivity<GetScannedProductsResponse, GetScannedProductsRequest, ShoppingViewModel>() {

    override fun provideViewModel(): ShoppingViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ShoppingViewModel::class.java)

    override fun provideToolbarTitle(): String = "Scanned products"

    override fun provideLayoutResource(): Int = R.layout.activity_shopping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.subtitle = "Shopping cart ID: " + prefs.cartId
    }
}
