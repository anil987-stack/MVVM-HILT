package com.ailogictask.android.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ailogictask.android.R
import com.ailogictask.android.data.model.Product
import com.ailogictask.android.databinding.ItemProductBinding

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback()) {

    private val expandedItems = mutableSetOf<Int>()

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")

        fun bind(product: Product) {
            binding.productName.text = product.title
            binding.prodName.text = product.title
            binding.prodDes.text = product.description
            binding.prodPrice.text = "₹${product.price}"

            binding.imageProduct.load(product.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.product_ic)
                error(R.drawable.product_ic)
            }

            val isExpanded = expandedItems.contains(product.id)
            binding.prodDetLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE


            binding.downArrow.animate().rotation(if (isExpanded) 180f else 0f).setDuration(200).start()

            binding.productHeader.setOnClickListener {
                if (isExpanded) {
                    expandedItems.remove(product.id)
                } else {
                    expandedItems.add(product.id)
                }
                notifyItemChanged(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(old: Product, new: Product) = old.id == new.id
        override fun areContentsTheSame(old: Product, new: Product) = old == new
    }
}
