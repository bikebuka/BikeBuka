package com.bikebuka.bikebuka.ui.view

import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.databinding.BikeItemBinding
import com.bikebuka.bikebuka.di.BaseRecyclerViewAdapter
import com.bikebuka.bikebuka.domain.Bike

class HomeAdapter : BaseRecyclerViewAdapter<Bike, BikeItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.bike_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<BikeItemBinding>,
        position: Int
    ) {
        //holder.binding.bikeImage = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}