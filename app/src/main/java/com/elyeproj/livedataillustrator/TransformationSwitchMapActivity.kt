package com.elyeproj.livedataillustrator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_transformation_switch_map.*
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class TransformationSwitchMapActivity : AppCompatActivity() {

    val liveDataA = MutableLiveData<String>()
    val liveDataB = MutableLiveData<String>()
    val liveDataSwitch = MutableLiveData<Boolean>()

    private val changeObserverA = Observer<String> { value ->
        value?.let {
            txt_livedata_a.text = it
        }
    }

    private val changeObserverB = Observer<String> { value ->
        value?.let {
            txt_livedata_b.text = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transformation_switch_map)
        title = "Transformation Switch Map"

        if (savedInstanceState != null) {
            setFragmentControlButtonText()
        }

        liveDataA.observe(this, changeObserverA)
        liveDataB.observe(this, changeObserverB)


        btn_livedata_a.setOnClickListener {
            txt_livedata_a.resetLoader()
            launch {
                delay(1000)
                liveDataA.postValue((1..9999).random())
            }
        }

        btn_livedata_b.setOnClickListener {
            txt_livedata_b.resetLoader()
            launch {
                delay(1000)
                liveDataB.postValue((1..9999).random())
            }
        }

        btn_control_fragment.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, TransformationSwitchMapFragment())
                        .addToBackStack("").commit()
            } else {
                supportFragmentManager.popBackStack()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
        }

        liveDataSwitch.value = switch_live_data.isChecked
        switch_live_data.setOnCheckedChangeListener { _, isChecked ->
            liveDataSwitch.value = isChecked
        }
    }

    private fun setFragmentControlButtonText() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            btn_control_fragment.text = "Add Fragment"
        } else {
            btn_control_fragment.text = "Remove Fragment"
        }
    }
}
