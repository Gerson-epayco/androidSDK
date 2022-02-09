package com.example.epaycosdk.ui.customer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import co.epayco.android.Epayco
import co.epayco.android.models.Authentication

import co.epayco.android.models.Client
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.databinding.DeleteTokenFragmentBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class DeleteTokenFragment : PrincipalFragment() {

    private lateinit var deleteTokenViewModel: DeleteTokenViewModel
    private var _binding: DeleteTokenFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteTokenViewModel =
            ViewModelProvider(this).get(DeleteTokenViewModel::class.java)

        val epayco = Epayco(auth)
        val client = Client()

        _binding = DeleteTokenFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        deleteTokenViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            System.out.println("click")

            val franchiseField = binding.EditTextFranchise 
            val franchise = franchiseField.text.toString()

            val maskField = binding.EditTextMask 
            val mask = maskField.text.toString()

            val customerIdField = binding.EditTextCustomerId 
            val customerId = customerIdField.text.toString()


            client.setFranchise(franchise)
            client.setMask(mask)
            client.setCustomer_id(customerId)


            epayco.deleteTokenCustomer(client, object : EpaycoCallback {
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

    fun sendFeedback(button: View?) {
        // Do click handling here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}