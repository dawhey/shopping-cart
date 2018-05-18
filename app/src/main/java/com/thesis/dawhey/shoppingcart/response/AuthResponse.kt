package com.thesis.dawhey.shoppingcart.response

data class AuthResponse(val token: String?,
                        @Transient override val status: ResponseStatus) : Response(status)