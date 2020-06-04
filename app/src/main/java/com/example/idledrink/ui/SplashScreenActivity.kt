package com.example.idledrink.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.idledrink.MainActivity
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.ui.roomdialog.JoinOrCreateRoomDialog

import androidx.fragment.app.FragmentManager
import com.example.idledrink.R
import com.example.idledrink.Utils
import java.util.*


class SplashScreenActivity : AppCompatActivity(), NameDialog.NameDialogListener,
    JoinOrCreateRoomDialog.JoinOrCreateRoomDialogListener {

    private lateinit var logo: ImageView
    private lateinit var buttonStartLine: FrameLayout
    private lateinit var buttonStart: Button
    private lateinit var buttonJoinOrCreateRoomLine: FrameLayout
    private lateinit var buttonJoinOrCreateRoom: Button
    private lateinit var tvTitle: TextView
    private lateinit var loadingPanek: FrameLayout
    private lateinit var prefs: SharedPreferences
    private lateinit var playerName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        this.tvTitle = findViewById(R.id.splash_tv)
        this.logo = findViewById(R.id.splash_logo)
        this.buttonStart = findViewById(R.id.splash_button_start)
        this.buttonStartLine = findViewById(R.id.splash_button_start_line)
        this.buttonJoinOrCreateRoom = findViewById(R.id.splash_button_newgame)
        this.buttonJoinOrCreateRoomLine = findViewById(R.id.splash_button_newgame_line)
        this.loadingPanek = findViewById(R.id.splash_loadingPanel)
        this.playerName = findViewById(R.id.splash_player_name)
        this.prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE)
        val needPlayerName = when (Utils.getSharedPrefString("playerName", this)) {
            "" -> true
            else -> false
        }

        if (needPlayerName) {
            NameDialog(this).show()
        } else {
            launchAnimation()
        }

        buttonStart.setOnClickListener {
            buttonStart.visibility = View.GONE
            buttonStartLine.visibility = View.GONE
            buttonJoinOrCreateRoom.visibility = View.GONE
            buttonJoinOrCreateRoomLine.visibility = View.GONE
            logo.visibility = View.GONE
            playerName.visibility = View.GONE
            loadingPanek.visibility = View.VISIBLE
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonJoinOrCreateRoom.setOnClickListener {
            val fragmentManager: FragmentManager  = getSupportFragmentManager();
            val joinOrCreateRoomDialog:  JoinOrCreateRoomDialog = JoinOrCreateRoomDialog(this)
            joinOrCreateRoomDialog.show(fragmentManager, "fragment_edit_name");        }
    }

    private fun launchAnimation() {
        logo.visibility = View.VISIBLE
        playerName.visibility = View.VISIBLE
        playerName.text = "Bonjour drinker ${prefs.getString(getString(R.string.playername_key), "")}"

        this.logo.startAnimation(AnimationUtils.loadAnimation(this,
            R.anim.splash_in
        ))
        Handler().postDelayed( {
            buttonStartLine.visibility = View.VISIBLE
            buttonStart.visibility = View.VISIBLE
            buttonJoinOrCreateRoomLine.visibility = View.VISIBLE
            buttonJoinOrCreateRoom.visibility = View.VISIBLE
        }, 1500)    }

    override fun onNameDialogPositiveClick(dialog: NameDialog, name: String) {
        val userId = UUID.randomUUID().toString()
        Utils.writeSharedPreferenceString("playerName", name, this)
        Utils.writeSharedPreferenceString("userId", userId, this)

        FireBaseManager.instance.writeNewUser(userId, name)
        dialog.dismiss()
        launchAnimation()
    }

    override fun onJoinRoomDialogPositiveClick(dialog: JoinOrCreateRoomDialog, name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateRoomDialogPositiveClick(dialog: JoinOrCreateRoomDialog, name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
