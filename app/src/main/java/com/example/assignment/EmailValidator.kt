package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.collection.emptyLongSet


class EmailValidator : Fragment() {


    @SuppressLint("SuspiciousIndentation", "MissingInflatedId", "CommitTransaction")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_email_validator, container, false)

        val btn = view.findViewById<Button>(R.id.button)
        val editText = view.findViewById<EditText>(R.id.mobile)

            btn.setOnClickListener{
                val btn1 = editText.text.toString()
                if(btn1.length == 10){
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,PinValidate()).addToBackStack(null).commit()
                    Toast.makeText(requireContext(), "Valid Mobile Number", Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(requireContext(), "InValid Mobile Number", Toast.LENGTH_SHORT).show()
                }
            }

            return view
    }

}