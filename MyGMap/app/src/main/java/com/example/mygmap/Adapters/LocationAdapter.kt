package com.example.mygmap.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygmap.Location
import com.example.mygmap.R

class LocationAdapter(var context: Context,var locations: MutableList<Location>,var onclicklistened:OnClickListened) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
interface OnClickListened{
    fun itemclicked(position: Int)
}
    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun binddata(location: Location) {
var demolocation = itemView.findViewById<TextView>(R.id.tvexpLoc)
            demolocation.text= location.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.expplace,parent,false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int){
        var location = locations[position]
        holder.binddata(location)
        holder.itemView.findViewById<TextView>(R.id.tvexpLoc).setOnClickListener {
            onclicklistened.itemclicked(position)
        }
    }

    override fun getItemCount(): Int {
        return  locations.size
    }

}
