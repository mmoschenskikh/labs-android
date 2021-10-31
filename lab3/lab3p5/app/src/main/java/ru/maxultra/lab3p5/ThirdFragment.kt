package ru.maxultra.lab3p5

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.maxultra.lab3p5.base.BaseFragment
import ru.maxultra.lab3p5.databinding.FragmentThirdBinding

class ThirdFragment : BaseFragment<FragmentThirdBinding>(FragmentThirdBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toFirstButton.setOnClickListener {
            findNavController().popBackStack(R.id.firstFragment, false)
        }
        binding.toSecondButton.setOnClickListener { findNavController().navigateUp() }
    }
}
