package com.thesis.dawhey.shoppingcart.response

import com.thesis.dawhey.shoppingcart.models.Product

data class GetScannedProductsResponse(override val status: ResponseStatus, val products: Set<Product>) : Response()
