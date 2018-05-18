package com.thesis.dawhey.shoppingcart.response

data class BindToCartResponse(@Transient override val status: ResponseStatus) : Response(status)