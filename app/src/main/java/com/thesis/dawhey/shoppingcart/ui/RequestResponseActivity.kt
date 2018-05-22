package com.thesis.dawhey.shoppingcart.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.request.Request
import com.thesis.dawhey.shoppingcart.response.Response
import com.thesis.dawhey.shoppingcart.viewmodels.RequestResponseViewModel
import com.thesis.dawhey.shoppingcart.viewmodels.ViewStatus
import kotlinx.android.synthetic.main.activity_login.*

abstract class RequestResponseActivity<T: Response, S: Request, V: RequestResponseViewModel<T, S>> : AppCompatActivity() {

    open val viewModel: V by lazy {
        provideViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutResource())
        supportActionBar?.title = provideToolbarTitle()
        supportActionBar?.setBackgroundDrawable(getDrawable(R.drawable.primary_gradient))
        addViewStatusObserver()
    }

    private fun addViewStatusObserver() {
        viewModel.viewStatus.observe(
                this, Observer {status: ViewStatus? ->
            when (status) {
                ViewStatus.LOADING -> onLoading()
                ViewStatus.API_ERROR -> onApiError()
                ViewStatus.SUCCESS -> onSuccess()
                ViewStatus.CONNECTION_ERROR -> onConnectionError()
            }
        })
    }

    open fun onConnectionError() {
        progressBarHolder.visibility = View.GONE
        Snackbar.make(findViewById(android.R.id.content), "Connection error. Try again later.", Snackbar.LENGTH_SHORT).show()
    }

    open fun onApiError() {
        progressBarHolder.visibility = View.GONE
    }

    open fun onSuccess() {
        progressBarHolder.visibility = View.GONE
    }

    open fun onLoading() {
        progressBarHolder.visibility = View.VISIBLE
    }

    abstract fun provideViewModel(): V

    abstract fun provideToolbarTitle(): String

    abstract fun provideLayoutResource(): Int

    fun startActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
        finish()
    }
}