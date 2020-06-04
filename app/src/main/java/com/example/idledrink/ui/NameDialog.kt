package com.example.idledrink.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.example.idledrink.R

class NameDialog(context: Context): Dialog(context) {

    private var listener: NameDialogListener

    interface NameDialogListener {
        fun onNameDialogPositiveClick(dialog: NameDialog, name: String)
    }

    lateinit var validatebutton: Button
    lateinit var nameEditText: EditText

    init {
        setCancelable(false)
        try {
            listener = context as NameDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NameDialogListener"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.name_dialog)
        this.validatebutton = findViewById(R.id.dialog_name_button_validate)
        this.nameEditText = findViewById(R.id.dialog_name_edit_text)

        validatebutton.setOnClickListener {
            listener.onNameDialogPositiveClick(this, nameEditText.text.toString())
        }
    }
}