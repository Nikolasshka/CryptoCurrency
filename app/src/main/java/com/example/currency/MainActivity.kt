package com.example.currency


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currency.fragments.CurrenciesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, CurrenciesListFragment(), null)
                    .commit()
        }
    }
}