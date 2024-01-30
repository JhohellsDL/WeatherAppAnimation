package com.example.weatherappanimation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class AdapterDaily(
    private val onClickListener: (Weather) -> Unit
) : ListAdapter<Weather, ItemViewHolderDaily>(ReadingComparator()) {

    class ReadingComparator : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.locationName == newItem.locationName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderDaily {
        return ItemViewHolderDaily.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolderDaily, position: Int) {
        val currentItem = getItem(position)
        holder.bind(
            currentItem,
            onClickListener)
    }

}