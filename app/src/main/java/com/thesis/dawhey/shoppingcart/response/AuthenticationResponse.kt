package com.thesis.dawhey.shoppingcart.response

data class AuthenticationResponse(val token: String?,
                                  override val status: ResponseStatus) : Response(status)