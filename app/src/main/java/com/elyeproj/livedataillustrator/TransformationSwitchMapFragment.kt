package com.elyeproj.livedataillustrator

import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mutable_live_data.*

class TransformationSwitchMapFragment : androidx.fragment.app.Fragment() {

    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_fragment.text = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transformation_switch_map, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val transformSwitchedLiveData =
                Transformations.switchMap(
                        (activity as TransformationSwitchMapActivity).liveDataSwitch) { switchToB ->
            if (switchToB) {
                (activity as TransformationSwitchMapActivity).liveDataB
            } else {
                (activity as TransformationSwitchMapActivity).liveDataA
            }
        }

        transformSwitchedLiveData.observe(this, changeObserver)
    }
}
