package com.example.convidados.ui.viewholder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.ui.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.tvGuestName.text = guest.name

        bind.btEdit.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.btDelete.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remover convidade!")
                .setMessage("Tem certeza que deseja remover esse convidade?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        }

    }
}