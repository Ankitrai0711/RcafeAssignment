package com.example.assignment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.assignment.databinding.FragmentOtpBinding

class Otp : Fragment() {

    // Declare the ViewBinding variable
    private lateinit var binding: FragmentOtpBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout using ViewBinding
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_otp, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedText = arguments?.getString("phone_no")
        binding.otpOnMobileNo.text = receivedText
        binding.sentPaasOnNo.text = receivedText


        moveCursor()
        //intializing variables for id's

        // Set up back button on click on back image
        binding.backOtp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
        binding.otpButton.setOnClickListener {
            nextFragment()
        }
    }

     private fun moveCursor(){
         val pinFields = listOf(binding.otpOne, binding.otpTwo, binding.otpThree, binding.otpFour, binding.otpFive, binding.otpSix)

         pinFields.forEachIndexed { index, editText ->
             editText.addTextChangedListener(object : TextWatcher {
                 override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                     // Not used
                 }

                 override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                     if (s.isNullOrEmpty() && before > 0) { // If the user delete a character
                         if (index > 0) { // Move to the previous pin
                             pinFields[index - 1].requestFocus()
                         }
                     }
                 }

                 override fun afterTextChanged(s: Editable?) {
                     if (s?.length == 1 && index < pinFields.size - 1) { // If the user enters a character
                         // Move to the next field
                             pinFields[index + 1].requestFocus()
                         }
                         else{
                             nextFragment()
                         }

                 }
             })
         }
     }
    fun nextFragment(){

        val combinedNumber = binding.otpOne.text.toString() +
                binding.otpTwo.text.toString() +
                binding.otpThree.text.toString() +
                binding.otpFour.text.toString() +
                binding.otpFive.text.toString() +
                binding.otpSix.text.toString()
        if(combinedNumber.length<6){
            binding.showErrorInOtp.text = getString(R.string.FillField)
        }
        else {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PinValidate())
                .commit()
        }
    }
}
