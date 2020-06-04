package com.example.idledrink.ui.build

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.Utils
import com.example.idledrink.adapter.BuildAdapter
import com.example.idledrink.database.entity.BuildingEntity
import com.example.idledrink.database.entity.StatEntity
import com.example.idledrink.model.buildings.ABuilding

interface BuildingObserver {
    fun onItemUpdated(item: ABuilding, modification: BuildingEntity.UPADTE)
    fun onPruductionUpdated(atret: Float, cl: Float, sv: Float)
}

class BuildFragment : Fragment(), BuildingObserver {

    private lateinit var buildViewModel: BuildViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        buildViewModel = ViewModelProviders.of(this, Utils.viewModelFactory { BuildViewModel(this.activity?.application!!) }).get(BuildViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_build, container, false)
        val buildRecyclerView: RecyclerView = root.findViewById(R.id.rv_build)
        buildRecyclerView.adapter = BuildAdapter(context!!, this)
        buildRecyclerView.layoutManager = LinearLayoutManager(context)
        buildViewModel.text.observe(viewLifecycleOwner, Observer {
            (buildRecyclerView.adapter as BuildAdapter).builds = it
        })

        return root
    }

    override fun onItemUpdated(item: ABuilding, modification: BuildingEntity.UPADTE) {
        buildViewModel.updateData(item, modification)
    }

    override fun onPruductionUpdated(atret: Float, cl: Float, sv: Float) {
        buildViewModel.updateStats(atret, cl, sv)
    }

}
