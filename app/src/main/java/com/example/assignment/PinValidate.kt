package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class PinValidate : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pin_validate, container, false)
        val back = view.findViewById<ImageView>(R.id.back)
        back.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,EmailValidator()).commit()
        }
        return view
    }

}