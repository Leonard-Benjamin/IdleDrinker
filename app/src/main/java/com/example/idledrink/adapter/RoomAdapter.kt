package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.Room
import com.example.idledrink.database.firebase.User

class RoomAdapter(val context: Context, val joinOrConitnueOnClick : (Room) -> Unit) : RecyclerView.Adapter<com.example.idledrink.adapter.RoomAdapter.ViewHolder>(){
    var rooms: ArrayList<Room> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_room_row, parent, false))
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holder: RoomAdapter.ViewHolder, position: Int) {
        holder.bind(rooms[position])
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private var name: TextView = view.findViewById(R.id.rv_room_name)
        private var players: TextView = view.findViewById(R.id.rv_room_players)
        private var joinButton: Button = view.findViewById(R.id.rv_room_join_button)

        fun bind(item: Room) {
            this.name.text = item.name
            this.players.text = item.users.size.toString() + " Joueur(s)"
            item.users.forEach {
                if (it.uuid == Utils.getSharedPrefString("userId", context)) {
                    joinButton.background = context.resources.getDrawable(R.drawable.button_blue_background)
                    joinButton.text = "Continuer"
                }
            }

            joinButton.setOnClickListener {
                item.users.add(User(Utils.getSharedPrefString("userId", context), Utils.getSharedPrefString("playerName", context)))
                joinOrConitnueOnClick(item)
            }
        }
    }
}