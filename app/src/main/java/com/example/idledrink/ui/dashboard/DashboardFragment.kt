package com.example.idledrink.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.adapter.BuildAdapter
import com.example.idledrink.adapter.DashBoardAdapter
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.Message
import com.example.idledrink.ui.ABaseFragment

interface MessageCallback {
    fun onMessageRead(message: Message)
    fun onDeleteMessageRequested(message: Message)
}

class DashboardFragment : Fragment(), MessageCallback {



    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var sendMessageButton: Button
    private lateinit var messageEditText: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProviders.of(this, Utils.viewModelFactory {
            DashboardViewModel(this.activity?.application!!)
        }).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val rvDashBoard: RecyclerView = root.findViewById(R.id.rv_dashBoard)
        sendMessageButton = root.findViewById(R.id.dashBoard_message_send_button)
        messageEditText = root.findViewById(R.id.dashBoard_message_editText)
        rvDashBoard.adapter = DashBoardAdapter(context!!, this)
        rvDashBoard.layoutManager = LinearLayoutManager(context)
        dashboardViewModel.mutableList.observe(viewLifecycleOwner, Observer {
            (rvDashBoard.adapter as DashBoardAdapter).data = it
            (rvDashBoard.adapter as DashBoardAdapter).notifyDataSetChanged()
            rvDashBoard.smoothScrollToPosition((rvDashBoard.adapter as DashBoardAdapter).data.size)
        })

        sendMessageButton.setOnClickListener {
            val user = activity?.let { it1 -> Utils.getSharedPrefString("playerName", it1) }
            val userId = activity?.let { it1 -> Utils.getSharedPrefString("userId", it1) }

            dashboardViewModel.sendMessage(messageEditText.text.toString(), user!!, userId!!)
            messageEditText.text.clear()
        }
        return root
    }

    override fun onMessageRead(message: Message) {
        dashboardViewModel.updateMessage(message)
    }

    override fun onDeleteMessageRequested(message: Message) {
        dashboardViewModel.deleteMessage(message)
    }
}
