package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import io.reactivex.Single

class ShoppingViewModel(application: Application) : RequestResponseViewModel<GetScannedProductsResponse, GetScannedProductsRequest>(application) {

    val dataRepository: DataRepository = DataRepositoryImpl()

    override var request: GetScannedProductsRequest = GetScannedProductsRequest(prefs.token)

    override fun provideObservableResultData(request: GetScannedProductsRequest): Single<GetScannedProductsResponse> =
            dataRepository.getScannedProducts(request)
}