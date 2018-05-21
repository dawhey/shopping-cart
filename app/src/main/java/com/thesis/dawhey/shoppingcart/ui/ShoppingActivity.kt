package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.SubscriptionEventListener
import com.thesis.dawhey.shoppingcart.BuildConfig
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.adapters.ProductsAdapter
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import com.thesis.dawhey.shoppingcart.viewmodels.ShoppingViewModel
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : RequestResponseActivity<GetScannedProductsResponse, GetScannedProductsRequest, ShoppingViewModel>() {

    private val dataRepository: DataRepository = DataRepositoryImpl()

    private val adapter: ProductsAdapter by lazy {
        ProductsAdapter()
    }

    override fun provideViewModel(): ShoppingViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ShoppingViewModel::class.java)

    override fun provideToolbarTitle(): String = getString(R.string.scanned_products)

    override fun provideLayoutResource(): Int = R.layout.activity_shopping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.subtitle = getString(R.string.cart_id_subtitle) + prefs.cartId
        productsView.layoutManager = LinearLayoutManager(this)
        productsView.adapter = adapter

        viewModel.products.observe(this, Observer {
            adapter.products = it!!
            adapter.notifyDataSetChanged()
        })

        swipeRefreshView.setOnRefreshListener { viewModel.request() }
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

    override fun onResume() {
        super.onResume()
        viewModel.request()
    }

    override fun onSuccess() {
        swipeRefreshView.isRefreshing = false
    }

    override fun onConnectionError() {
        swipeRefreshView.isRefreshing = false
    }

    override fun onApiError() {
        swipeRefreshView.isRefreshing = false
    }

    override fun onLoading() {
        swipeRefreshView.isRefreshing = true
    }
}