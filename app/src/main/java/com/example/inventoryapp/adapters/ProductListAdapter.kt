package com.example.inventoryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.database.ProductTable
import com.example.inventoryapp.databinding.ProductListViewBinding

private const val ITEM_VIEW_TYPE_PRODUCT = 1

class ProductListAdapter(private val productClickListener: ProductClickListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_PRODUCT -> ProductViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> {
                val prod = getItem(position) as DataItem.ProductItem
                holder.bind(prod.product, productClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.ProductItem -> ITEM_VIEW_TYPE_PRODUCT
        }
    }

    class ProductViewHolder private constructor(val binding: ProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListViewBinding.inflate(layoutInflater, parent, false)

                return ProductViewHolder(binding)
            }
        }

        fun bind(product: ProductTable, clickListener: ProductClickListener) {
            binding.product = product
            binding.productClickListener = clickListener

            binding.executePendingBindings()
        }

    }

}

sealed class DataItem {
    abstract val id: Long

    data class ProductItem(val product: ProductTable) : DataItem() {
        override val id: Long
            get() = product.productId
    }

}

class ProductDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }

}

class ProductClickListener(val clickListener: (productId: Long) -> Unit) {
    fun onClick(product: ProductTable) = clickListener(product.productId)
}