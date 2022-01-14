package com.example.epaycosdk.ui.daviplata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.epayco.android.Epayco
import co.epayco.android.models.Daviplata
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.databinding.DaviplataFragmentBinding
import org.json.JSONException
import org.json.JSONObject

class DaviplataFragment : PrincipalFragment() {

    private lateinit var viewModel: DaviplataViewModel
    private var _binding: DaviplataFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(DaviplataViewModel::class.java)
        val epayco = Epayco(auth)

        _binding = DaviplataFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {


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


            val daviplata = Daviplata()

            daviplata.docType = docType
            daviplata.document = document
            daviplata.name = name
            daviplata.lastName = lastName
            daviplata.email = email
            daviplata.invoice = invoice
            daviplata.description = description
            daviplata.value = value.toFloat()
            daviplata.tax = tax.toFloat()
            daviplata.taxBase = taxBase.toFloat()
            daviplata.ico = ico.toFloat()
            daviplata.phone = phone.toInt()
            daviplata.currency = currency
            daviplata.ip = ip
            daviplata.urlResponse = urlResponse
            daviplata.urlConfirmation = urlConfirmation
            daviplata.indCountry = indCountry
            daviplata.methodConfirmation = methodConfirmation
            daviplata.country = country
            daviplata.city = city
            daviplata.address = address


            epayco.createDaviplata(daviplata, object : EpaycoCallback {
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