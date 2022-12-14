package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.OldData
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.databinding.ItemListBinding



class MainAdapter(val onClickListener: OnClickListener) : ListAdapter<Asteroid,MainAdapter.AsteroidViewHolder>(DiffCallback) {

    class AsteroidViewHolder(private var binding:ItemListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(oldData: Asteroid){
            binding.asteroidItem =oldData
            binding.executePendingBindings()
        }


    }
    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {

        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroidItem = getItem(position)
        holder.itemView.setOnClickListener{
               onClickListener.onClick(asteroidItem)
        }
        holder.bind(asteroidItem)
    }


    class OnClickListener(val clickListener: (asteroid:Asteroid) -> Unit){
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }

}
