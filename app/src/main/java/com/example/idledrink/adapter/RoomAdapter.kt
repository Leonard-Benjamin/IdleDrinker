package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.Room
import com.example.idledrink.database.firebase.User
import com.example.idledrink.ui.roomdialog.RoomCallback

class RoomAdapter(val context: Context, val roomCallback: RoomCallback) : RecyclerView.Adapter<com.example.idledrink.adapter.RoomAdapter.ViewHolder>(){
    var rooms: ArrayList<Room> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_room_row, parent, false))
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holder: RoomAdapter.ViewHolder, position: Int) {
        holder.bindView(rooms[position])
    }

    inner class ViewHolder (view: View) : ABaseViewHolderWithPopupMenu<Room>(view, R.menu.message_onclick_menu) {

        private var name: TextView = view.findViewById(R.id.rv_room_name)
        private var players: TextView = view.findViewById(R.id.rv_room_players)
        private var joinButton: Button = view.findViewById(R.id.rv_room_join_button)

        override fun unBindView() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onItemMenuClick(itemId: Int) {
            when(itemId) {
                R.id.delete -> roomCallback.onDeleteRoomRequested(rooms[adapterPosition])
            }
        }

        override fun bindView(item: Room) {
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
                roomCallback.onClickJoinOrContinue(item)
            }
        }
    }
}