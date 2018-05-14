package com.thesis.dawhey.shoppingcart.repositories

import com.thesis.dawhey.shoppingcart.models.User
import com.thesis.dawhey.shoppingcart.response.AuthenticationResponse

interface UserRepository {
    fun authenticateUser(user: User): AuthenticationResponse
}