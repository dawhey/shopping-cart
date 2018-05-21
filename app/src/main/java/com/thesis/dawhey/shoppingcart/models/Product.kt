package com.thesis.dawhey.shoppingcart.models

data class Product(val id: Int,
                   val name: String,
                   val weight: Float,
                   val price: Float,
                   val weightVariance: Float)