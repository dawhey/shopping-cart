package com.thesis.dawhey.shoppingcart.response

import com.google.gson.annotations.SerializedName

enum class ResponseStatus(val value: String) {
    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("failure")
    FAILURE("failure")
}