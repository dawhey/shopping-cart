package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.ProductScanRequest
import com.thesis.dawhey.shoppingcart.response.ProductScanResponse

class ScanningViewModel(application: Application): RequestResponseViewModel<ProductScanResponse, ProductScanRequest>(application) {

    override lateinit var request: ProductScanRequest

    private val dataRepository: DataRepository = DataRepositoryImpl()

    override fun provideObservableResultData(request: ProductScanRequest) = dataRepository.scanProduct(request)
}