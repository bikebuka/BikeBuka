package com.bikebuka.bikebuka.ui.view

import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.databinding.BikeItemBinding
import com.bikebuka.bikebuka.di.BaseRecyclerViewAdapter
import com.bikebuka.bikebuka.service.response.Bike

class HomeAdapter : BaseRecyclerViewAdapter<Bike, BikeItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.bike_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<BikeItemBinding>,
        position: Int
    ) {
        holder.binding.bike = items[position]
        holder.binding.bookBike.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
        holder.binding.root.setOnClickListener {
            itemListener?.invoke(it, items[position], position)
        }
    }
}