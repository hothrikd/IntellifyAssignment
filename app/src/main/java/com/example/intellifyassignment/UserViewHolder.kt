package com.example.intellifyassignment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    fun bind(user: User,onClick:(name:String,photo:String,city:String,mailaddr:String,id:String)->Unit) = with(itemView){
        titleTv.text = user.name
        Picasso.get().load(user.thumbImage).placeholder(R.drawable.defaultavatar).error(R.drawable.defaultavatar).into(userImgView)
        setOnClickListener{
            onClick.invoke(user.name,user.thumbImage,user.city,user.mailaddr,user.uid)
        }
    }
}