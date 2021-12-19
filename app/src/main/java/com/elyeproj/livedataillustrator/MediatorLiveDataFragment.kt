package com.elyeproj.livedataillustrator

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mutable_live_data.*

class MediatorLiveDataFragment : androidx.fragment.app.Fragment() {

    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_fragment.text = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mediator_live_data, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mediatorLiveData = MediatorLiveData<String>()
        mediatorLiveData.addSource((activity as MediatorLiveDataActivity).liveDataA) { mediatorLiveData.value = "A:$it" }
        mediatorLiveData.addSource((activity as MediatorLiveDataActivity).liveDataB) { mediatorLiveData.value = "B:$it" }
        mediatorLiveData.observe(this, changeObserver)
    }
}
