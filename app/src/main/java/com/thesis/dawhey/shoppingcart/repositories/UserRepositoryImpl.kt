package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.response.AuthenticationResponse
import com.thesis.dawhey.shoppingcart.response.ResponseStatus
import io.reactivex.Single

class UserRepositoryImpl : UserRepository {

    override fun authenticateUser(user: User): Single<AuthenticationResponse> {
        return Single.just(AuthenticationResponse(status = ResponseStatus.SUCCESS, token = "a1b2c3d4"))
    }

    override fun saveUserToken(token: String?) {
        prefs.token = token
    }

    override fun getUserToken(): String? {
        return prefs.token
    }
}