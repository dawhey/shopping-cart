package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.api.ApiService
import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.AuthenticationResponse
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.response.ResponseStatus
import io.reactivex.Single

class UserRepositoryImpl : UserRepository {

    private val api: ApiService by lazy {
        ApiService.create()
    }

    override fun authenticateUser(user: User): Single<AuthenticationResponse> {
        return api.authenticateUser(user)
    }

    override fun saveUserToken(token: String?) {
        prefs.token = token
    }

    override fun getUserToken(): String? {
        return prefs.token
    }

    override fun bindUserToCart(request: BindToCartRequest): Single<BindToCartResponse> {
        return api.bindToCart(request)
    }
}