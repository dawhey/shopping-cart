package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.prefs
import com.thesis.dawhey.shoppingcart.repositories.UserRepository
import com.thesis.dawhey.shoppingcart.repositories.UserRepositoryImpl
import com.thesis.dawhey.shoppingcart.response.AuthenticationResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application): AndroidViewModel(application){

    val user = User()
    val viewStatus = MutableLiveData<ViewStatus>()
    val userRepository: UserRepository = UserRepositoryImpl()
    val isAuthenticated: Boolean = prefs.token != null

    fun authenticate() {
        viewStatus.value = ViewStatus.LOADING
        userRepository.authenticateUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((object : DisposableSingleObserver<AuthenticationResponse>() {
                    override fun onError(e: Throwable) {
                        viewStatus.value = ViewStatus.ERROR
                    }

                    override fun onSuccess(t: AuthenticationResponse) {
                        viewStatus.value = ViewStatus.SUCCESS
                        userRepository.saveUserToken(t.token)
                    }
                }))
    }
}