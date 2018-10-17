package com.elyeproj.livedataillustrator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_mutable_livedata.setOnClickListener{
            startActivity(Intent(this, MutableLiveDataActivity::class.java))
        }
        btn_mediator_livedata.setOnClickListener{
            startActivity(Intent(this, MediatorLiveDataActivity::class.java))
        }
        btn_transformation_map.setOnClickListener{
            startActivity(Intent(this, TransformationMapActivity::class.java))
        }
        btn_transformation_switchmap.setOnClickListener{
            startActivity(Intent(this, TransformationSwitchMapActivity::class.java))
        }
    }
}
