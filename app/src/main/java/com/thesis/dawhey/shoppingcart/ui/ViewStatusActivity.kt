package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.thesis.dawhey.shoppingcart.request.Request
import com.thesis.dawhey.shoppingcart.response.Response
import com.thesis.dawhey.shoppingcart.viewmodels.AbstractViewModel
import com.thesis.dawhey.shoppingcart.viewmodels.ViewStatus
import kotlinx.android.synthetic.main.activity_login.*

abstract class ViewStatusActivity<T: Response, S: Request, V: AbstractViewModel<T, S>> : AppCompatActivity() {

    open val viewModel: V by lazy {
        provideViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutResource())
        supportActionBar?.title = provideToolbarTitle()
        addViewStatusObserver()
    }

    private fun addViewStatusObserver() {
        viewModel.viewStatus.observe(
                this, Observer {status: ViewStatus? ->
            when (status) {
                ViewStatus.LOADING -> onLoading()
                ViewStatus.ERROR -> onError()
                ViewStatus.SUCCESS -> onSuccess()
            }
        })
    }

    open fun onError() {
        progressBar.visibility = View.GONE
    }

    open fun onSuccess() {
        progressBar.visibility = View.GONE
    }

    open fun onLoading() {
        progressBar.visibility = View.VISIBLE
    }

    abstract fun provideViewModel(): V

    abstract fun provideToolbarTitle(): String

    abstract fun provideLayoutResource(): Int
}