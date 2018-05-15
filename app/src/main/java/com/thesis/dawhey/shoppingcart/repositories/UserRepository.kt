package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.response.AuthenticationResponse
import io.reactivex.Single

interface UserRepository {

    fun authenticateUser(user: User): Single<AuthenticationResponse>

    fun saveUserToken(token: String?)

    fun getUserToken(): String?

}