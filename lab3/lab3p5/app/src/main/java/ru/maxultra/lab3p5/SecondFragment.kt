package ru.maxultra.lab3p5

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.maxultra.lab3p5.base.BaseFragment
import ru.maxultra.lab3p5.databinding.FragmentSecondBinding

@Suppress("DEPRECATION")
class SecondFragment : BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toFirstButton.setOnClickListener { findNavController().navigateUp() }
        binding.toThirdButton.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
    }
}

