package com.thesis.dawhey.shoppingcart.pusher

import com.thesis.dawhey.shoppingcart.models.Product

interface PushEventListener {

    fun onProductAdded(p: Product)

    fun onProductRemoved(p: Product)
}