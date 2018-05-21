package com.thesis.dawhey.shoppingcart.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.models.Product

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    var products: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductViewHolder = ProductViewHolder(parent?.inflate(R.layout.item_product))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) = holder!!.bind(products[position])
}