package com.elyeproj.livedataillustrator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MutableLiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutable_live_data)
        title = "Mutable Live Data"
    }
}
