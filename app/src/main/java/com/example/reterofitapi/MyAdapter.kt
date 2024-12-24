package com.example.reterofitapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(var context: MainActivity,var productlist: List<Product>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
           holder.brand.text=productlist[position].brand
        holder.category.text=productlist[position].category
        holder.discount.text=productlist[position].discountPercentage.toString()
        holder.price.text=productlist[position].price.toString()
        holder.title.text=productlist[position].title
        holder.description.text=productlist[position].description
        holder.image.setImageResource(R.drawable.ic_launcher_background)
        Picasso.get().load(productlist[position].thumbnail).into(holder.image);

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     var image=itemView.findViewById<ImageView>(R.id.image)
        var brand=itemView.findViewById<TextView>(R.id.brand)
        var category=itemView.findViewById<TextView>(R.id.category)
        var discount=itemView.findViewById<TextView>(R.id.discount)
        var price=itemView.findViewById<TextView>(R.id.price)
        var title=itemView.findViewById<TextView>(R.id.title)
        var description=itemView.findViewById<TextView>(R.id.description)
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

}
