package com.example.currency.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.currency.Adapter.BaseAdapter
import com.example.currency.Adapter.CurrenciesAdapter
import com.example.currency.R
import com.example.currency.di.App
import com.example.currency.mvp.contract.CurrenciesContract
import com.example.currency.mvp.presenter.CurrenciesPresenter
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
@Module
class CurrenciesListFragment : BaseListFragment(), CurrenciesContract.View {
    @Inject
    lateinit var presenter: CurrenciesPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()
    }
    override fun createAdapterInstance(): BaseAdapter<*> {
        return CurrenciesAdapter()
    }
    override fun addCurrency(currency: CurrenciesAdapter.Currency) {
        viewAdapter.add(currency)
    }
    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }
    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }
    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }
    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }
    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }
    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}