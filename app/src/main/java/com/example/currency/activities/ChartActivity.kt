package com.example.currency.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.currency.R
import com.example.currency.chart.LatestChart
import com.example.currency.di.App
import com.example.currency.mvp.contract.LatestChartContract
import com.example.currency.mvp.presenter.LatestChartPresenter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_chart.*
import java.text.DecimalFormat
import javax.inject.Inject

class ChartActivity : AppCompatActivity(), OnChartValueSelectedListener, LatestChartContract.View {

    @Inject
    lateinit var latestChart: LatestChart

    @Inject
    lateinit var presenter: LatestChartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        App.appComponent.inject(this)
        presenter.attach(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")
        val marketCapRank = intent.getIntExtra("marketCapRank", 0)
        val symbol = intent.getStringExtra("symbol")
        val marketCap = intent.getStringExtra("marketCap")
        val marketCapChangePercentage24h = intent?.getDoubleExtra("marketCapChangePercentage24h", 0.0)
        val priceChangePercentage24h = intent?.getDoubleExtra("priceChangePercentage24h", 0.0)
        val totalVolume = intent?.getFloatExtra("totalVolume", 0.0f)
        val ath = intent?.getDoubleExtra("ath", 0.0)
        val athChangePercentage = intent?.getDoubleExtra("athChangePercentage", 0.0)
        val circulatingSupply = intent?.getDoubleExtra("circulatingSupply", 0.0)
        val totalSupply = intent?.getDoubleExtra("totalSupply", 0.0)
        val image = intent.getStringExtra("image")

        Glide.with(this).load(image).into(ivCurrencyDetailIcon)

        supportActionBar?.title = name

        val df = DecimalFormat("#")
        df.maximumFractionDigits = 2

        tvDetailMarketCapRank.text = marketCapRank.toString()
        tvMarketCapChange.text = marketCapChangePercentage24h.toString()
        tvATH.text = ath.toString()
        tvAthChange.text = df.format(athChangePercentage)
        tvCirculatingSupply.text = df.format(circulatingSupply)
        tvTotalSupply.text = df.format(totalSupply)

        presenter.makeChart(intent.getStringExtra("id").toString())

        latestChart.initChart(chartCurrency)
    }

    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEntryToChart(date: Float, value: Float) {

        latestChart.addEntry(value, date)
    }

    override fun addEntryToChart(value: Float, date: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        progressChart.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressChart.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

