package com.example.convidados.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.ui.listener.OnGuestListener
import com.example.convidados.ui.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList : List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuest(list : List<GuestModel>){
        notifyDataSetChanged()
        guestList = list
    }

    fun updatePresentsGuests(list : List<GuestModel>){
        notifyDataSetChanged()
        guestList = list
    }

    fun updateAbsentsGuests(list : List<GuestModel>){
        notifyDataSetChanged()
        guestList = list
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}