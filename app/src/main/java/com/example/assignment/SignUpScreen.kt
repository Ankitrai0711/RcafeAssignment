package com.example.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.example.assignment.databinding.FragmentSignUpScreenBinding

class EmailValidator : Fragment() {

    // Declare the ViewBinding variable
    private lateinit var binding: FragmentSignUpScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using ViewBinding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_screen, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()

        // Skip action
        binding.skip.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Dashboard())
                .commit()
        }


    }

    private fun setOnClickListener() {
        binding.button.setOnClickListener {
            val mobile = binding.mobile.text.toString()
            val regex = Regex("^[6-9]\\d{9}\$") // Starts with 6-9 and has 10 digits

            // Check if the checkbox is checked
            if (!binding.checkbox.isChecked) {
                binding.errorMsg.text = getString(R.string.checkBox)
            }

            // Validate the mobile number format
            else if (!mobile.matches(regex)) {
                binding.errorMsg.text = getString(R.string.Invalid_Number)

            }

            // Check if all digits are not the same
            else if (mobile.toSet().size == 1) {
                binding.errorMsg.text = getString(R.string.No_Same)

            }

            // If all pass than go to next page
            else {
                // Creating an intance for otp fragment
                val otpFragment = Otp()

                // adding argument to fragment for sending data
                val bundle = Bundle()
                bundle.putString("phone_no", mobile)
                otpFragment.arguments = bundle

                // go to the Otp fragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, otpFragment).addToBackStack(null).commit()

                // Hide the error message
                binding.errorMsg.visibility = View.GONE
            }

        }
    }
}
