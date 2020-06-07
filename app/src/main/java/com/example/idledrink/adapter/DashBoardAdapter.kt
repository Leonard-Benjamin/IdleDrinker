package com.example.idledrink.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.Message
import com.example.idledrink.database.firebase.User
import com.example.idledrink.ui.dashboard.MessageCallback
import java.util.*

class DashBoardAdapter(context: Context,
                       data: ArrayList<Message>,
                       listener: ABaseAdapterListener,
                       withPopoupMenu: Boolean,
                       val messageCallback: MessageCallback)
    : ABaseAdapter<Message>(data, context, withPopoupMenu, listener) {

    inner class ViewHolder (view: View) : ABaseViewHolderWithPopupMenu<Message>(view) {

        private var tvUserNameString: TextView = view.findViewById(R.id.rv_dashboard_username_tv)
        private var tvTextString: TextView = view.findViewById(R.id.rv_dashboard_message_tv)
        private var tvReadByCount: TextView = view.findViewById(R.id.rv_dashBoard_read_by_count_tv)

        private fun setRead(message: Message) {
            val player = Utils.getSharedPrefString("playerName", context)
            val playerId = Utils.getSharedPrefString("userId", context)
            val user = User(playerId, player)
            if (!message.readBy.contains(user)) {
                message.addUserRead(user)
                messageCallback.onMessageRead(message)
            }
        }

        override fun onItemMenuClick(itemId: Int) {
            when(itemId) {
                R.id.delete -> messageCallback.onDeleteMessageRequested(datas[adapterPosition])
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
    }
}