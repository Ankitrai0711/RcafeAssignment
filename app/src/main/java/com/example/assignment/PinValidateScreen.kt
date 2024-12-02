package com.example.assignment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.assignment.databinding.FragmentPinValidateBinding

class PinValidate : Fragment() {

    // Declare ViewBinding variable
    private lateinit var binding: FragmentPinValidateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using ViewBinding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pin_validate, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        moveCursor()
        buttonClickListner()

        // Set up back button Logo to go to next Fragment
        binding.back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
    }

    private fun moveCursor() {
        val pinFields = listOf(
            binding.pinOne,
            binding.pinTwo,
            binding.pinThree,
            binding.pinFour,
            binding.pinFive,
            binding.pinSix,
            binding.pinSeven,
            binding.pinEight
        )

        pinFields.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty() && before > 0) { // If the user delete a character
                        if (index > 0) { // Move to the previous pin
                            pinFields[index - 1].requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty()) { // If the user enters a character
                        if (index < pinFields.size - 1) { // Move to the next field
                            pinFields[index + 1].requestFocus()
                        } else {
                            nextFragment()
                        }
                    }
                }
            })
        }
    }

    private fun buttonClickListner() {
        // Set click listener for the button
        binding.buttonSetPin.setOnClickListener {

            nextFragment()

        }
    }

    private fun nextFragment() {
        val combinedNumber = binding.pinOne.text.toString() +
                binding.pinTwo.text.toString() +
                binding.pinThree.text.toString() +
                binding.pinFour.text.toString()

        // Combine confirm pin fields
        val confirmPassword =
            binding.pinFive.text.toString() +
                    binding.pinSix.text.toString() +
                    binding.pinSeven.text.toString() +
                    binding.pinEight.text.toString()

        // Check if either pin or confirm pin is empty
        if (combinedNumber.isEmpty() || confirmPassword.isEmpty()) {
            binding.showErrorInPin.text = getString(R.string.FillField)

        }
        // Check if both pins match
        else if (combinedNumber == confirmPassword) {
            requireActivity().supportFragmentManager.popBackStackImmediate()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Dashboard())
                .commit()
        }
        else{
            binding.showErrorInPin.text = getString(R.string.incorrect)
        }
    }
}
