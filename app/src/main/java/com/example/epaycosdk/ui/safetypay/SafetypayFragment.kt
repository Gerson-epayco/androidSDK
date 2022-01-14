package com.example.epaycosdk.ui.safetypay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import co.epayco.android.Epayco
import co.epayco.android.models.Safetypay
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.databinding.SafetypayFragmentBinding
import org.json.JSONException
import org.json.JSONObject

class SafetypayFragment : PrincipalFragment() {


    private lateinit var viewModel: SafetypayViewModel
    private var _binding: SafetypayFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(SafetypayViewModel::class.java)
        val epayco = Epayco(auth)

        _binding = SafetypayFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {


            val cashField = binding.EditTextCash as EditText
            val cash = cashField.text.toString()

            val endDateField = binding.EditTextEndDate as EditText
            val endDate = endDateField.text.toString()


            val docTypeField = binding.EditTextDocType as EditText
            val docType = docTypeField.text.toString()

            val documentField = binding.EditTextDocument as EditText
            val document = documentField.text.toString()

            val ipField = binding.EditTextIP as EditText
            val ip = ipField.text.toString()

            val nameField = binding.EditTextName as EditText
            val name = nameField.text.toString()

            val lastNameField = binding.EditTextLastName as EditText
            val lastName = lastNameField.text.toString()

            val emailField = binding.EditTextEmail as EditText
            val email = emailField.text.toString()

            val invoiceField = binding.EditTextInvoice as EditText
            val invoice = invoiceField.text.toString()

            val descriptionField = binding.EditTextDescription as EditText
            val description = descriptionField.text.toString()

            val valueField = binding.EditTextValue as EditText
            val value = valueField.text.toString()

            val taxField = binding.EditTextTax as EditText
            val tax = taxField.text.toString()

            val taxBaseField = binding.EditTextTaxBase as EditText
            val taxBase = taxBaseField.text.toString()

            val icoField = binding.EditTextIco as EditText
            val ico = icoField.text.toString()

            val phoneField = binding.EditTextPhone as EditText
            val phone = phoneField.text.toString()

            val currencyField = binding.EditTextCurrency as EditText
            val currency = currencyField.text.toString()

            val countryField = binding.EditTextCountry as EditText
            val country = countryField.text.toString()

            val urlResponseField = binding.EditTextUrlResponse as EditText
            val urlResponse = urlResponseField.text.toString()

            val urlResponseFieldPointer = binding.EditTextUrlResponsePointer as EditText
            val urlResponsePointer = urlResponseFieldPointer.text.toString()

            val urlConfirmationField = binding.EditTextUrlConfirmation as EditText
            val urlConfirmation = urlConfirmationField.text.toString()

            val methodConfirmationField = binding.EditTextMethodConfirmation as EditText
            val methodConfirmation = methodConfirmationField.text.toString()

            val indCountryField = binding.EditTextIndCountry as EditText
            val indCountry = indCountryField.text.toString()

            val cityField = binding.EditTextCity as EditText
            val city = cityField.text.toString()

            val addressField = binding.EditTextAddress as EditText
            val address = addressField.text.toString()


            val safetypay = Safetypay()

            safetypay.cash = cash
            safetypay.endDate = endDate
            safetypay.docType = docType
            safetypay.document = document
            safetypay.name = name
            safetypay.lastName = lastName
            safetypay.email = email
            safetypay.invoice = invoice
            safetypay.description = description
            safetypay.value = value.toFloat()
            safetypay.tax = tax.toFloat()
            safetypay.taxBase = taxBase.toFloat()
            safetypay.ico = ico.toFloat()
            safetypay.phone = phone
            safetypay.currency = currency
            safetypay.ip = ip
            safetypay.urlResponse = urlResponse
            safetypay.urlResponsePointer = urlResponsePointer
            safetypay.urlConfirmation = urlConfirmation
            safetypay.indCountry = indCountry
            safetypay.methodConfirmation = methodConfirmation
            safetypay.country = country
            safetypay.city = city
            safetypay.address = address
            safetypay.typeIntegration = "Android"


            epayco.createSafetypay(safetypay, object : EpaycoCallback {
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