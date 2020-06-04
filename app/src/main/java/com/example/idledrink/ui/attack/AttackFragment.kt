package com.example.idledrink.ui.attack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.idledrink.R

class AttackFragment : Fragment() {

    private lateinit var attackViewModel: AttackViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        attackViewModel =
            ViewModelProviders.of(this).get(AttackViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_attack, container, false)
        val textView: TextView = root.findViewById(R.id.text_attack)
        attackViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
