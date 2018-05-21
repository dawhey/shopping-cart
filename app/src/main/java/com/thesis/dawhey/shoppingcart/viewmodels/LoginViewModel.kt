package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.DataRepository
import com.thesis.dawhey.shoppingcart.repositories.DataRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import io.reactivex.Single

class LoginViewModel(application: Application): RequestResponseViewModel<AuthResponse, AuthRequest>(application){

    override lateinit var request: AuthRequest

    private val dataRepository: DataRepository = DataRepositoryImpl()

    val isAuthenticated: Boolean = !prefs.token.isEmpty()

    override fun provideObservableResultData(request: AuthRequest): Single<AuthResponse> = dataRepository.authenticateUser(request)

    override fun onSuccess(response: AuthResponse) {
        super.onSuccess(response)
        prefs.token = response.token ?: ""
    }
}