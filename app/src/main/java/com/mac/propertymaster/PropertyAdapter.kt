package com.mac.propertymaster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PropertyAdapter(val context: Context) : RecyclerView.Adapter<PropertyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_property, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        when (position) {
            0 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_1))
            1 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_2))
            2 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_3))
            3 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_4))
            4 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_5))
            5 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_6))
            6 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_7))
            7 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_8))
            8 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_9))
            9 -> holder.image.setImageDrawable(context.resources.getDrawable(R.drawable.image_3))
        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
    }
}