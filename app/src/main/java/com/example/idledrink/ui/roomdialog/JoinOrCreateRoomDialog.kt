package com.example.idledrink.ui.roomdialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.adapter.ABaseAdapterListener
import com.example.idledrink.adapter.RoomAdapter
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.Room
import com.example.idledrink.database.firebase.User
import java.util.*
import kotlin.collections.ArrayList

interface RoomCallback {
    fun onClickJoinOrContinue(room: Room)
    fun onDeleteRoomRequested(room: Room)
}

class JoinOrCreateRoomDialog(context: Context): DialogFragment(), RoomCallback,
    ABaseAdapterListener {

    private var listener: JoinOrCreateRoomDialogListener
    private lateinit var rvRooms: RecyclerView
    private lateinit var newRoomLayout: ConstraintLayout
    lateinit var adapter: RoomAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var createButton: Button

    interface JoinOrCreateRoomDialogListener {
        fun onJoinRoomDialogPositiveClick(dialog: JoinOrCreateRoomDialog, name: String)
        fun onCreateRoomDialogPositiveClick(dialog: JoinOrCreateRoomDialog, name: String)
    }

    lateinit var validatebutton: Button
    lateinit var nameEditText: EditText
    lateinit var viewModel: RoomViewModel

    init {
        try {
            listener = context as JoinOrCreateRoomDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NameDialogListener"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        		return inflater.inflate(com.example.idledrink.R.layout.join_or_create_room_dialog, container);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState)
        this.isCancelable = true

        this.rvRooms = view.findViewById(R.id.rrv_ooms_joc_dialog)
        this.createButton = view.findViewById(R.id.join_or_create_create_button)
        this.validatebutton = view.findViewById(R.id.join_or_create_validate_new_button)
        this.newRoomLayout = view.findViewById(R.id.join_or_create_new_layout)
        viewModel = ViewModelProviders.of(this, Utils.viewModelFactory {
            RoomViewModel(this.activity?.application!!)
        }).get(RoomViewModel::class.java)
        this.adapter = context?.let {
            RoomAdapter(it, ArrayList(), this, true, this)
        }!!
        this.layoutManager = GridLayoutManager(context, 2)
        this.rvRooms.adapter = this.adapter
        this.rvRooms.layoutManager = this.layoutManager

        this.viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            (this.rvRooms.adapter as RoomAdapter).datas = it
            this.adapter.notifyDataSetChanged()
        })

        this.createButton.setOnClickListener {
            this.newRoomLayout.visibility = View.VISIBLE
            this.createButton.visibility = View.GONE
        }

        this.validatebutton.setOnClickListener {
            this.nameEditText = view.findViewById(R.id.joijn_or_create_new_edit_text)
            if (this.nameEditText.text != null) {
                FireBaseManager.instance.writeRoom(UUID.randomUUID().toString(),
                    nameEditText.text.toString(),
                    arrayListOf(User(Utils.getSharedPrefString("userId", context!!),
                        Utils.getSharedPrefString("playerName", context!!)))
                )
                this.newRoomLayout.visibility = View.GONE
                this.createButton.visibility = View.VISIBLE
            }
        }
    }

    override fun onClickJoinOrContinue(room: Room) {
        viewModel.updateRoom(room)
    }

    override fun onDeleteRoomRequested(room: Room) {
        viewModel.deleteRoom(room)
    }

    override fun getLayout(): Int {
        return R.layout.rv_room_row
    }

    override fun getMenuLayout(): Int {
        return R.menu.item_onclick_menu
    }
}