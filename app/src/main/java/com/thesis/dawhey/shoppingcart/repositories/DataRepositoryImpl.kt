package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.api.ApiService
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.request.ProductScanRequest
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
        prefs.cartId = ""
    }

    override fun authenticateUser(request: AuthRequest) = api.authenticateUser(request)

    override fun bindUserToCart(request: BindToCartRequest) = api.bindToCart(request)

    override fun getScannedProducts(request: GetScannedProductsRequest) = api.getCartProducts(request.token)

    override fun scanProduct(request: ProductScanRequest) = api.scanProduct(request)
}