package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.thesis.dawhey.shoppingcart.models.Product
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.pusher.PushEventListener
import com.thesis.dawhey.shoppingcart.pusher.PusherManager
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import io.reactivex.Single


class ShoppingViewModel(application: Application) : RequestResponseViewModel<GetScannedProductsResponse, GetScannedProductsRequest>(application), PushEventListener {

    val pusherManager: PusherManager = PusherManager(this)

    val products = MutableLiveData<MutableList<Product>>()

    private val dataRepository: DataRepository = DataRepositoryImpl()

    override var request: GetScannedProductsRequest = GetScannedProductsRequest(prefs.token)

    override fun provideObservableResultData(request: GetScannedProductsRequest): Single<GetScannedProductsResponse> =
            dataRepository.getScannedProducts(request)

    override fun onSuccess(response: GetScannedProductsResponse) {
        super.onSuccess(response)
        products.value = response.products.sortedBy { it.name }.toMutableList()
    }

    override fun onProductAdded(p: Product) {
        with(mutableListOf<Product>()) {
            addAll(this@ShoppingViewModel.products.value!!)
            add(p)
            this@ShoppingViewModel.products.postValue(this)
        }
    }

    override fun onProductRemoved(p: Product) {
        with(mutableListOf<Product>()) {
            addAll(this@ShoppingViewModel.products.value!!)
            remove(p)
            this@ShoppingViewModel.products.postValue(this)
        }
    }
}