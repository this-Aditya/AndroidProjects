package com.example.yelpp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantAdapter(var context: Context,var restraunts: MutableList<YelpRestaraunts>,var gonewsite :goToSite) : RecyclerView.Adapter<RestaurantAdapter.RestarauntViewHolder>() {

    interface goToSite{
        fun gotosite(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestarauntViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.example_restaurant,parent,false)
        return RestarauntViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestarauntViewHolder, position: Int) {
var restraunt = restraunts[position]
    holder.binddata(restraunt,context)
        holder.itemView.findViewById<TextView>(R.id.tvname).setOnClickListener {
            gonewsite.gotosite(position)
        }
    }

    override fun getItemCount(): Int {
return  restraunts.size
    }
    class RestarauntViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagee = itemView.findViewById<ImageView>(R.id.imageView)
        var name = itemView.findViewById<TextView>(R.id.tvname)
        var price = itemView.findViewById<TextView>(R.id.tvprice)
        var city = itemView.findViewById<TextView>(R.id.tvcity)
        var address = itemView.findViewById<TextView>(R.id.tvaddress)
        var category = itemView.findViewById<TextView>(R.id.tvcategory)
        var rating = itemView.findViewById<RatingBar>(R.id.ratingBar)
        fun binddata(restraunt: YelpRestaraunts,context:Context) {
            var imageuri = restraunt.image_url
            Picasso.with(context).load(imageuri).into(imagee)
            name.text = restraunt.name
            price.text =restraunt.price
            city.text = restraunt.locationdata.city
            address.text = restraunt.locationdata.adderss
            category.text= restraunt.categories[0].title
            rating.rating = restraunt.rating.toFloat()
        }
    }
}
