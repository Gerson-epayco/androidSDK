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
import co.epayco.android.models.Client
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.R
import com.example.epaycosdk.databinding.AddNewTokenFragmentBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class AddNewToken : PrincipalFragment() {

    private lateinit var viewModel: AddNewTokenViewModel
    private var _binding: AddNewTokenFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(AddNewTokenViewModel::class.java)
        val epayco = Epayco(auth)
        val client = Client()

        _binding = AddNewTokenFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            System.out.println("click")
            val customerIdField = binding.EditTextCustomerId 
            val customerId = customerIdField.text.toString()

            val tokenIdField = binding.EditTextTokenId 
            val tokenID = tokenIdField.text.toString()


            client.setCustomer_id(customerId)
            client.setTokenId(tokenID)

            epayco.addNewToken(client, object : EpaycoCallback {
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