package com.example.idledrink.ui

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.idledrink.R

class IdleDrinkDialog(context: Context, icon: Drawable?, message: String, buildName: String) : Dialog(context) {

    lateinit var idDialogBuildName: TextView
    lateinit var idDialogMessage: TextView
    lateinit var idDialogIcon: ImageView
    lateinit var idDialogButton: Button
    var icon: Drawable?
    var message: String = "null"
    var buildName: String = "null"

    init {
        setCancelable(false)
        this.icon = icon
        this.message = message
        this.buildName = buildName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.idle_drink_dialog)
        idDialogIcon = findViewById(R.id.id_dialog_icon)
        idDialogMessage = findViewById(R.id.id_dialog_message)
        idDialogButton = findViewById(R.id.id_dialog_button)
        idDialogBuildName = findViewById(R.id.id_dialog_build_name)

        idDialogBuildName.text = this.buildName

        if (this.icon != null)
            idDialogIcon.setImageDrawable(icon)
        idDialogMessage.text = message

        idDialogButton.setOnClickListener { this.dismiss() }
    }
}