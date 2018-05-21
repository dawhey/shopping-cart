package com.thesis.dawhey.shoppingcart.pusher

import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.thesis.dawhey.shoppingcart.BuildConfig
import com.thesis.dawhey.shoppingcart.models.Product

class PusherManager(private val listener: PushEventListener) {

    private val gson: Gson = Gson()

    private val channel = "shopping-cart-channel"
    private val cluster = "eu"
    private val addedEvent = "added-event"
    private val removedEvent = "removed-event"

    init {
        val options = PusherOptions()
        options.setCluster(cluster)
        val pusher = Pusher(BuildConfig.PUSHER_API_KEY, options)
        val channel = pusher.subscribe(channel)

        channel.bind(addedEvent) { _, _, data ->
            listener.onProductAdded(gson.fromJson(data, Product::class.java))
        }

        channel.bind(removedEvent) { _, _, data ->
            listener.onProductRemoved(gson.fromJson(data, Product::class.java))
        }

        pusher.connect()
    }
}