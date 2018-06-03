package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.viewmodels.ScanningDeviceViewModel
import kotlinx.android.synthetic.main.activity_scanning_device.*

class ScanningDeviceActivity : RequestResponseActivity<BindToCartResponse, BindToCartRequest, ScanningDeviceViewModel>() {

    override fun provideViewModel(): ScanningDeviceViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ScanningDeviceViewModel::class.java)

    override fun provideToolbarTitle(): String = "Select scanning device"

    override fun provideLayoutResource(): Int = R.layout.activity_scanning_device

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanning_device)
        buttonNext.setOnClickListener {
            viewModel.request = BindToCartRequest(prefs.token, inputCartId.text.toString())
            viewModel.request() }

        if (viewModel.isCartAssigned) startActivity(ShoppingActivity::class.java)
    }

    override fun onApiError() {
        super.onApiError()
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.invalid_device_id), Snackbar.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        super.onSuccess()
        prefs.deviceId = inputCartId.text.toString()
        startActivity(ShoppingActivity::class.java)
    }
}
