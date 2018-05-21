package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.request.GetScannedProductsRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import io.reactivex.Single

interface DataRepository {

    fun getScannedProducts(request: GetScannedProductsRequest): Single<GetScannedProductsResponse>

    fun authenticateUser(request: AuthRequest): Single<AuthResponse>

    fun bindUserToCart(request: BindToCartRequest): Single<BindToCartResponse>

    fun logout()
}