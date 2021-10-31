package com.example.epaycosdk.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.epaycosdk.R
import com.example.epaycosdk.databinding.FragmentSlideshowBinding
import android.widget.Button

import android.widget.EditText
import android.widget.Toast


class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val nameField = binding.EditTextName as EditText
            val name = nameField.text.toString()

            val emailField = binding.EditTextEmail as EditText
            val email = emailField.text.toString()


            System.out.println("click")
            System.out.println(email)
            System.out.println(nameField)
        }

        return root
    }

    fun sendFeedback(button: View?) {
        // Do click handling here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}