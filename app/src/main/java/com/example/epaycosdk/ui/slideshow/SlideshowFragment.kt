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
import co.epayco.android.Epayco
import co.epayco.android.models.Authentication
import co.epayco.android.models.Card
import org.json.JSONException

import org.json.JSONObject

import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import java.lang.Exception


class SlideshowFragment : PrincipalFragment() {

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
        val epayco = Epayco(auth)
        val card = Card()

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            System.out.println("click")
            val numberField = binding.EditTextNumber as EditText
            val number = numberField.text.toString()

            val monthField = binding.EditTextMonth as EditText
            val month = monthField.text.toString()

            val yearField = binding.EditTextYear as EditText
            val year = yearField.text.toString()

            val cvcField = binding.EditTextCVC as EditText
            val cvc = cvcField.text.toString()

            card.setNumber(number)
            card.setMonth(month)
            card.setYear(year)
            card.setCvc(cvc)

            epayco.createToken(card, object : EpaycoCallback {
                @Throws(JSONException::class)
                override fun onSuccess(data: JSONObject) {

                    val textView: TextView = binding.result
                    textView.text = data.toString()

                    System.out.println("onSuccess")

                }

                override fun onError(error: Exception) {}
            })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}