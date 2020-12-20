package com.example.currency.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.currency.Adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_currency.*

abstract class BaseListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    protected lateinit var viewAdapter: BaseAdapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        viewAdapter = createAdapterInstance()
        recyclerView = list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
    abstract fun createAdapterInstance(): BaseAdapter<*>
}