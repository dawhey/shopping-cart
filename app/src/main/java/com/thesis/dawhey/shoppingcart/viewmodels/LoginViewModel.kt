package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.UserRepository
import com.thesis.dawhey.shoppingcart.repositories.UserRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import io.reactivex.Single

class LoginViewModel(application: Application): AbstractViewModel<AuthResponse, AuthRequest>(application){

    override lateinit var request: AuthRequest

    private val userRepository: UserRepository = UserRepositoryImpl()

    val isAuthenticated: Boolean = !prefs.token.isNullOrEmpty()

    override fun provideObservableResultData(request: AuthRequest): Single<AuthResponse> = userRepository.authenticateUser(request)

    override fun onSuccess(response: AuthResponse) {
        super.onSuccess(response)
        userRepository.saveUserToken(response.token)
    }
}