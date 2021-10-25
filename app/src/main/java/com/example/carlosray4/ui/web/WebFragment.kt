package com.example.carlosray4.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carlosray4.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private lateinit var webViewModel: WebViewModel
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        webViewModel =
            ViewModelProvider(this).get(WebViewModel::class.java)

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val root: View = binding.root

        webViewModel.resultLiveMutable.observe(viewLifecycleOwner, Observer {
            binding.browser.loadUrl(it)
        })
        webViewModel.getURL()

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}