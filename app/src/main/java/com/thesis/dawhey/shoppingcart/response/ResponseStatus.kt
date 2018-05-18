package com.thesis.dawhey.shoppingcart.response

import com.google.gson.annotations.SerializedName

enum class ResponseStatus {
    @SerializedName("success")
    SUCCESS,

    @SerializedName("failure")
    FAILURE
}