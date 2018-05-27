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
import com.thesis.dawhey.shoppingcart.request.ProductScanRequest
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import com.thesis.dawhey.shoppingcart.response.ProductScanResponse
import com.thesis.dawhey.shoppingcart.response.ResponseStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ShoppingViewModel(application: Application) : RequestResponseViewModel<GetScannedProductsResponse, GetScannedProductsRequest>(application), PushEventListener {

    init {
        PusherManager(this)
    }

    val productScanStatus = MutableLiveData<ScanStatus>()

    val products = MutableLiveData<MutableList<Product>>()

    private val dataRepository: DataRepository = DataRepositoryImpl()

    override var request: GetScannedProductsRequest = GetScannedProductsRequest(prefs.token)

    override fun provideObservableResultData(request: GetScannedProductsRequest) = dataRepository.getScannedProducts(request)

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

    fun scanProduct(productId: String) {
        dataRepository.scanProduct(ProductScanRequest(prefs.cartId, productId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableSingleObserver<ProductScanResponse>() {
                    override fun onSuccess(t: ProductScanResponse) {
                        when (t.status) {
                            ResponseStatus.SUCCESS -> productScanStatus.value = ScanStatus.SCAN_OK
                            ResponseStatus.FAILURE -> productScanStatus.value = ScanStatus.SCAN_FAILURE
                        }
                    }

                    override fun onError(e: Throwable) {
                        viewStatus.value = ViewStatus.CONNECTION_ERROR
                    }
                })
    }
}