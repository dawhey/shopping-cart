package com.thesis.dawhey.shoppingcart.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.thesis.dawhey.shoppingcart.models.User

class LoginViewModel(application: Application): AndroidViewModel(application){
    var user = User()
}