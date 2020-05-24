package com.example.voicediary.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.voicediary.R
import kotlinx.android.synthetic.main.fragment_third.view.*


class ThirdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        view.button.setOnClickListener {
            findNavController().navigate(R.id.action_third_to_first)
        }
        return view
    }
}