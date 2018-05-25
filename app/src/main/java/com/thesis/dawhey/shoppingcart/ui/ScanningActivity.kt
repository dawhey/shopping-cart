package com.thesis.dawhey.shoppingcart.ui

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.thesis.dawhey.shoppingcart.viewmodels.ScanningViewModel
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScanningActivity :  Activity(){

    private lateinit var scannerView: ZBarScannerView

    private lateinit var viewModel: ScanningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZBarScannerView(this)
        setContentView(scannerView)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ScanningViewModel::class.java)
    }


    override fun onResume() {
        super.onResume()
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }
}