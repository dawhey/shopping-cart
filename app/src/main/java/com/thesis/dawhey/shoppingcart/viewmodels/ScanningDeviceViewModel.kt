package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import io.reactivex.Single

class ScanningDeviceViewModel(application: Application) : RequestResponseViewModel<BindToCartResponse, BindToCartRequest>(application) {

    val isCartAssigned = !prefs.deviceId.isEmpty()

    override lateinit var request: BindToCartRequest

    private val dataRepository: DataRepository = DataRepositoryImpl()

    override fun provideObservableResultData(request: BindToCartRequest) = dataRepository.bindUserToCart(request)
}