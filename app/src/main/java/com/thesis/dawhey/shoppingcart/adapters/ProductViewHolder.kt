package com.thesis.dawhey.shoppingcart.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.thesis.dawhey.shoppingcart.R
import com.thesis.dawhey.shoppingcart.models.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product) = with(itemView) {
        productNameView.text = product.name
        productPrice.text = resources.getString(R.string.price_place_holder, product.price.toString())
        productWeightView.text = resources.getString(R.string.weight_place_holder, product.weight.toInt().toString())
    }
}