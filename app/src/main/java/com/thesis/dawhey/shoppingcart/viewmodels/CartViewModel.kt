package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import com.thesis.dawhey.shoppingcart.repositories.UserRepository
import com.thesis.dawhey.shoppingcart.repositories.UserRepositoryImpl
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import io.reactivex.Single

class CartViewModel(application: Application) : RequestResponseViewModel<BindToCartResponse, BindToCartRequest>(application) {

    override lateinit var request: BindToCartRequest

    private val userRepository: UserRepository = UserRepositoryImpl()

    override fun provideObservableResultData(request: BindToCartRequest): Single<BindToCartResponse> = userRepository.bindUserToCart(request)
}