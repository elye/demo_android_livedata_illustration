package com.elyeproj.livedataillustrator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mutable_live_data.*
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MutableLiveDataActivity : AppCompatActivity() {

    val liveData1 = MutableLiveData<String>()

    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_livedata1.text = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutable_live_data)
        title = "Mutable Live Data"

        if (savedInstanceState != null) {
            setFragmentControlButtonText()
        }

        liveData1.observe(this, changeObserver)

        btn_livedata1.setOnClickListener {
            txt_livedata1.resetLoader()
            launch {
                delay(1000)
                liveData1.postValue(
                        (1..9999).random().toString()
                                .padStart(4, '0'))
            }
        }

        btn_control_fragment.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MutableLiveDataFragment())
                        .addToBackStack("").commit()
            } else {
                supportFragmentManager.popBackStack()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
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
