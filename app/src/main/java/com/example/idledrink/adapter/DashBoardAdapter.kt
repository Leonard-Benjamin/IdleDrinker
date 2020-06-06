package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.Message
import com.example.idledrink.database.firebase.User
import java.util.*

class DashBoardAdapter(val context: Context, val onReadMessage: (Message) -> Unit) : RecyclerView.Adapter<DashBoardAdapter.ViewHolder>(){

    var entries = ArrayList<Message>()

    fun setData(list: ArrayList<Message>) {
        this.entries = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_dashboard_row, parent, false))
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: DashBoardAdapter.ViewHolder, position: Int) {
        holder.bindView(entries[position])
    }

    inner class ViewHolder (view: View) : ABaseViewHolderWithPopupMenu<Message>(view, R.menu.message_onclick_menu) {

        private var tvUserNameString: TextView = view.findViewById(R.id.rv_dashboard_username_tv)
        private var tvTextString: TextView = view.findViewById(R.id.rv_dashboard_message_tv)
        private var tvReadByCount: TextView = view.findViewById(R.id.rv_dashBoard_read_by_count_tv)

        private fun setRead(message: Message) {
            val player = Utils.getSharedPrefString("playerName", context)
            val playerId = Utils.getSharedPrefString("userId", context)
            val user = User(playerId, player)
            if (!message.readBy.contains(user)) {
                message.addUserRead(user)
                onReadMessage(message)
            }
        }

        override fun bindView(item: Message) {
            if (Utils.getSharedPrefString("playerName", context) == item.sender.name) {
                tvUserNameString.text = context.getString(R.string.message_sender_is_you)
            } else {
                tvUserNameString.text = item.sender.name + " :"
            }
            tvTextString.text = item.text
            tvReadByCount.text = Utils.getDateFromMillis(item.insertedTime as Long)
            setRead(item)
        }

        override fun unBindView() {
            tvTextString.text = null
            tvUserNameString.text = null
            tvReadByCount.text = null
        }
    }
}