package ru.maxultra.lab3p5

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.maxultra.lab3p5.base.BaseFragment
import ru.maxultra.lab3p5.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment<FragmentFirstBinding>(FragmentFirstBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bnToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}
