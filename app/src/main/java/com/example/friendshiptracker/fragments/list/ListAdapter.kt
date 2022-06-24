package com.example.friendshiptracker.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var userList = emptyList<User>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false  ))

    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val curItem = userList[position]
        val id = holder.itemView.findViewById<TextView>(R.id.id_txt)
        id.text = (position+1).toString()

        val firstname = holder.itemView.findViewById<TextView>(R.id.list_name)
        firstname.text = curItem.firstName

        val where = holder.itemView.findViewById<TextView>(R.id.list_where)
        where.text = curItem.Where

        val When = holder.itemView.findViewById<TextView>(R.id.list_when)
        When.text = curItem.When

        val Why = holder.itemView.findViewById<TextView>(R.id.list_why)
        Why.text = curItem.Why

        val cardlayout = holder.itemView.findViewById<CardView>(R.id.cardview)

        //for dynamic colors
        val itemview = holder.itemView
        val rainbow = holder.itemView.context.resources.getIntArray(R.array.rainbow)
        cardlayout.setCardBackgroundColor(rainbow[position%12])


        cardlayout.setOnClickListener {

            val action = ListFragmentDirections.actionListFragmentToUpdate(curItem)
            holder.itemView.findNavController().navigate(action)
        }

    }



    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(user : List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }


}
