package com.example.mentorplustask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mentorplustask.model.DishModel

class MyAdapter(val dishList : ArrayList<DishModel>,val list : ArrayList<Int>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var onClickListener : OnClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.dish_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(holder is MyViewHolder){
            holder.price.text = "₹ ${dishList[position].price.toString()}"
            holder.dishDesc.text = dishList[position].dish_name
            holder.dishName.text = dishList[position].customisation[0]
            holder.dishCount.text = list[position].toString()
            holder.addButton.setOnClickListener{

                if(onClickListener!=null){
                    if(list[position]<5){
                        list[position]++
                        holder.dishCount.text=(list[position]).toString()
                        onClickListener!!.onClick(dishList[position].price,position,false)
                    }

                }

            }
            holder.subButton.setOnClickListener{

                if(onClickListener!=null){

                    if(list[position]>1){
                        list[position]--
                        holder.dishCount.text=(list[position]).toString()
                        onClickListener!!.onClick(-dishList[position].price,position,false)
                    }else{
                        list[position]--
                        holder.dishCount.text=(list[position]).toString()
                        onClickListener!!.onClick(-dishList[position].price,position,true)
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return dishList.size
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var price  =itemView.findViewById<TextView>(R.id.price)
        var dishName = itemView.findViewById<TextView>(R.id.dishDescription)
        var dishDesc = itemView.findViewById<TextView>(R.id.dishName)
        var addButton = itemView.findViewById<ImageView>(R.id.dish_add)
        val subButton = itemView.findViewById<ImageView>(R.id.dish_sub)
        val dishCount = itemView.findViewById<TextView>(R.id.dish_count)
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(price : Int,position: Int,isDelete : Boolean)
    }

}



