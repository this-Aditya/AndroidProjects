package com.example.mvvmpracticeretrofit2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpracticeretrofit2.data.model.Users
import com.example.mvvmpracticeretrofit2.databinding.RowLayoutBinding
import com.squareup.picasso.Picasso


class GithubAdapter(var context: Context,var users:List<Users> ): RecyclerView.Adapter<GithubAdapter.MyViewHolder>() {
class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun bindata(user: Users) {
binding.tvRow.text=user.login
        Picasso.get().load(user.avatar_url).into(binding.ivRow)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
var user = users[position]
        holder.bindata(user)
    }

    override fun getItemCount(): Int {
return users.size   }

}