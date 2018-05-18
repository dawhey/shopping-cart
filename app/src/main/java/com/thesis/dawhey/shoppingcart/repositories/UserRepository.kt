package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import io.reactivex.Single

interface UserRepository {

    fun authenticateUser(request: AuthRequest): Single<AuthResponse>

    fun saveUserToken(token: String?)

    fun getUserToken(): String?

    fun bindUserToCart(request: BindToCartRequest): Single<BindToCartResponse>
}