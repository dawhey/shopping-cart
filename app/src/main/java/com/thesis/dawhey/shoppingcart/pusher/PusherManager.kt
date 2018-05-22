package com.thesis.dawhey.shoppingcart.pusher

import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.thesis.dawhey.shoppingcart.BuildConfig
import com.thesis.dawhey.shoppingcart.models.Product

class PusherManager(private val listener: PushEventListener) {

    private val gson: Gson = Gson()

    private val CHANNEL = "shopping-cart-channel"
    private val CLUSTER = "eu"
    private val ADDED_EVENT = "added-event"
    private val REMOVED_EVENT = "removed-event"

    init {
        val options = PusherOptions()
        options.setCluster(CLUSTER)
        val pusher = Pusher(BuildConfig.PUSHER_API_KEY, options)
        val channel = pusher.subscribe(CHANNEL)

        channel.bind(ADDED_EVENT) { _, _, data ->
            listener.onProductAdded(gson.fromJson(data, Product::class.java))
        }

        channel.bind(REMOVED_EVENT) { _, _, data ->
            listener.onProductRemoved(gson.fromJson(data, Product::class.java))
        }

        pusher.connect()
    }
}