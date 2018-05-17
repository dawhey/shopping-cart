package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.UserRepository
import com.thesis.dawhey.shoppingcart.repositories.UserRepositoryImpl
import com.thesis.dawhey.shoppingcart.response.AuthenticationResponse
import com.thesis.dawhey.shoppingcart.response.ResponseStatus
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application): AbstractViewModel<AuthenticationResponse>(application){

    lateinit var user: User

    private val userRepository: UserRepository = UserRepositoryImpl()

    val isAuthenticated: Boolean = !prefs.token.isNullOrEmpty()

    override fun provideObservableData(): Single<AuthenticationResponse> = userRepository.authenticateUser(user)

    override fun onSuccess(response: AuthenticationResponse) {
        super.onSuccess(response)
        userRepository.saveUserToken(response.token)
    }
}