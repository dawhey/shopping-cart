package com.thesis.dawhey.shoppingcart.response

data class AuthResponse(val token: String?,
                        override var status: ResponseStatus) : Response()