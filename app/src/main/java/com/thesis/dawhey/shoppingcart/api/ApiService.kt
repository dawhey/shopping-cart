package com.thesis.dawhey.shoppingcart.api

import com.thesis.dawhey.shoppingcart.BuildConfig
import com.thesis.dawhey.shoppingcart.request.AuthRequest
import com.thesis.dawhey.shoppingcart.request.BindToCartRequest
import com.thesis.dawhey.shoppingcart.request.ProductScanRequest
import com.thesis.dawhey.shoppingcart.response.AuthResponse
import com.thesis.dawhey.shoppingcart.response.BindToCartResponse
import com.thesis.dawhey.shoppingcart.response.GetScannedProductsResponse
import com.thesis.dawhey.shoppingcart.response.ProductScanResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @POST("scan")
    fun scanProduct(@Body request: ProductScanRequest): Single<ProductScanResponse>

    @POST("authenticate")
    fun authenticateUser(@Body request: AuthRequest): Single<AuthResponse>

    @POST("bind")
    fun bindToCart(@Body request: BindToCartRequest): Single<BindToCartResponse>

    @GET("products")
    fun getCartProducts(@Query("token") token: String): Single<GetScannedProductsResponse>

    companion object {
        fun create(): ApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.interceptors().add(logging)

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .client(httpClientBuilder.build())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}