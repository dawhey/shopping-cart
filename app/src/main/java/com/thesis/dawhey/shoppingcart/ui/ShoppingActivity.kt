package com.thesis.dawhey.shoppingcart.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.adapters.ProductsAdapter
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import com.thesis.dawhey.shoppingcart.utils.ScanUtils
import com.thesis.dawhey.shoppingcart.viewmodels.ScanStatus
import com.thesis.dawhey.shoppingcart.viewmodels.ShoppingViewModel
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : RequestResponseActivity<GetScannedProductsResponse, GetScannedProductsRequest, ShoppingViewModel>() {

    private val dataRepository: DataRepository = DataRepositoryImpl()

    private val adapter: ProductsAdapter by lazy {
        ProductsAdapter()
    }

    override fun provideViewModel() = ViewModelProvider.AndroidViewModelFactory(application).create(ShoppingViewModel::class.java)

    override fun provideToolbarTitle() = getString(R.string.scanned_products)

    override fun provideLayoutResource() = R.layout.activity_shopping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.subtitle = getString(R.string.cart_id_subtitle) + prefs.cartId
        productsView.layoutManager = LinearLayoutManager(this)
        productsView.adapter = adapter

        viewModel.products.observe(this, Observer {
            if (it!!.isEmpty()) Snackbar.make(findViewById(android.R.id.content), getString(R.string.cart_empty), Snackbar.LENGTH_SHORT).show()
            adapter.products = it
            adapter.notifyDataSetChanged()
        })

        viewModel.productScanStatus.observe(this, Observer {
            when(it) {
                ScanStatus.SCAN_OK -> Snackbar.make(findViewById(android.R.id.content), "Product scanned.", Snackbar.LENGTH_SHORT).show()
                ScanStatus.SCAN_FAILURE -> Snackbar.make(findViewById(android.R.id.content), "Product scan failure.", Snackbar.LENGTH_SHORT).show()
            }
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
            R.id.scan -> {
                startScanningActivity()
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

    private fun startScanningActivity() {
        try {
            val intent = Intent(ScanUtils.SCAN_INTENT)
            intent.putExtra(ScanUtils.SCAN_MODE_KEY, ScanUtils.SCAN_MODE_VALUE)
            startActivityForResult(intent, 0)
        } catch (e: Exception) {
            val marketUri = Uri.parse(ScanUtils.ZXING_CLIENT_URI)
            val marketIntent = Intent(Intent.ACTION_VIEW,marketUri)
            startActivity(marketIntent)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val contents = intent.getStringExtra(ScanUtils.SCAN_RESULT)
                viewModel.scanProduct(contents)
            }
        }
    }
}
