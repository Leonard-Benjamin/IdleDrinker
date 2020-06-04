package com.example.idledrink.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.adapter.ShopAdapter

class ShopFragment : Fragment() {

    private lateinit var shopViewModel: ShopViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shop, container, false)
        val rvShop: RecyclerView = root.findViewById(R.id.rv_shop)
        rvShop.adapter = ShopAdapter(context!!)
        rvShop.layoutManager = LinearLayoutManager(context)
        shopViewModel.text.observe(viewLifecycleOwner, Observer {
            (rvShop.adapter as ShopAdapter).shopItems = it
        })
        return root
    }
}
