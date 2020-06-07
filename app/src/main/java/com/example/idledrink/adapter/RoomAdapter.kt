package com.example.idledrink.adapter

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.Room
import com.example.idledrink.database.firebase.User
import com.example.idledrink.ui.roomdialog.RoomCallback

class RoomAdapter(context: Context, data: ArrayList<Room>, listener: ABaseAdapterListener, withPopoupMenu: Boolean, val roomCallback: RoomCallback)
    : ABaseAdapter<Room>(data, context, withPopoupMenu, listener) {

    inner class ViewHolder (view: View) : ABaseViewHolderWithPopupMenu<Room>(view) {

        private var name: TextView = view.findViewById(R.id.rv_room_name)
        private var players: TextView = view.findViewById(R.id.rv_room_players)
        private var joinButton: Button = view.findViewById(R.id.rv_room_join_button)

        override fun onItemMenuClick(itemId: Int) {
            when(itemId) {
                R.id.delete -> roomCallback.onDeleteRoomRequested(datas[adapterPosition])
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