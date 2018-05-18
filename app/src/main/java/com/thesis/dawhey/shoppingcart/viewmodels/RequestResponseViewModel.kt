package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.thesis.dawhey.shoppingcart.request.Request
import com.thesis.dawhey.shoppingcart.response.Response
import com.thesis.dawhey.shoppingcart.response.ResponseStatus
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class RequestResponseViewModel<T: Response, S: Request>(application: Application): AndroidViewModel(application){

    val viewStatus = MutableLiveData<ViewStatus>()

    abstract var request: S

    fun request() {
        viewStatus.value = ViewStatus.LOADING
        provideObservableResultData(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableSingleObserver<T>() {

                    override fun onError(e: Throwable) = this@RequestResponseViewModel.onError(e)

                    override fun onSuccess(t: T) = this@RequestResponseViewModel.onSuccess(t)
                })
    }

    abstract fun provideObservableResultData(request: S): Single<T>

    open fun onError(e: Throwable) {
        viewStatus.value = ViewStatus.ERROR
    }

    open fun onSuccess(response: T) = when (response.status) {
        ResponseStatus.SUCCESS -> viewStatus.value = ViewStatus.SUCCESS
        ResponseStatus.FAILURE -> viewStatus.value = ViewStatus.ERROR
    }
}