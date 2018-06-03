package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.api.ApiService
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import io.reactivex.Single

class DataRepositoryImpl : DataRepository {

    private val api: ApiService by lazy {
        ApiService.create()
    }

    override fun logout() {
        prefs.token = ""
        prefs.deviceId = ""
    }

    override fun authenticateUser(request: AuthRequest): Single<AuthResponse> = api.authenticateUser(request)

    override fun bindUserToCart(request: BindToCartRequest): Single<BindToCartResponse> = api.bindToCart(request)

    override fun getScannedProducts(request: GetScannedProductsRequest): Single<GetScannedProductsResponse> = api.getCartProducts(request.token)
}