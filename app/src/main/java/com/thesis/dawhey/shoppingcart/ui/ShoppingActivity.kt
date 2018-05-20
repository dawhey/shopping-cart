package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import com.thesis.dawhey.shoppingcart.viewmodels.ShoppingViewModel

class ShoppingActivity : RequestResponseActivity<GetScannedProductsResponse, GetScannedProductsRequest, ShoppingViewModel>() {

    val dataRepository: DataRepository = DataRepositoryImpl()

    override fun provideViewModel(): ShoppingViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ShoppingViewModel::class.java)

    override fun provideToolbarTitle(): String = "Scanned products"

    override fun provideLayoutResource(): Int = R.layout.activity_shopping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.subtitle = "Shopping cart ID: " + prefs.cartId
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shopping_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logout -> {
                dataRepository.logout()
                startActivity(LoginActivity::class.java)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
