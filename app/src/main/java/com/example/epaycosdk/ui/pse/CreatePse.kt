package com.example.epaycosdk.ui.pse

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
import co.epayco.android.models.ChargeSub
import co.epayco.android.models.Subscription
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.R
import com.example.epaycosdk.databinding.CreatePseFragmentBinding
import com.example.epaycosdk.ui.subscriptions.PaySubscriptionViewModel
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import co.epayco.android.models.Pse




class CreatePse : PrincipalFragment() {
    private lateinit var viewModel: CreatePseViewModel
    private var _binding: CreatePseFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(CreatePseViewModel::class.java)
        val epayco = Epayco(auth)
        val subscription = Subscription()

        _binding = CreatePseFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val bankField = binding.EditTextBank as EditText
            val bank = bankField.text.toString()

            val typePersonField = binding.EditTextTypePerson as EditText
            val typePerson = typePersonField.text.toString()

            val docTypeField = binding.EditTextDocType as EditText
            val docType = docTypeField.text.toString()

            val docNumberField = binding.EditTextDocNumber as EditText
            val docNumber = docNumberField.text.toString()

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

            val extra1Field = binding.EditTextExtra1 as EditText
            val extra1 = extra1Field.text.toString()

            val extra2Field = binding.EditTextExtra1 as EditText
            val extra2 = extra2Field.text.toString()

            val extra3Field = binding.EditTextExtra3 as EditText
            val extra3 = extra3Field.text.toString()

            val cityField = binding.EditTextCity as EditText
            val city = cityField.text.toString()

            val deptoField = binding.EditTextDepto as EditText
            val depto = deptoField.text.toString()

            val addressField = binding.EditTextAddress as EditText
            val address = addressField.text.toString()

            val pse = Pse()
            pse.bank = bank
            pse.typePerson = typePerson
            pse.docType = docType
            pse.docNumber = docNumber
            pse.name = name
            pse.lastName = lastName
            pse.email = email
            pse.invoice = invoice
            pse.description = description
            pse.value = value
            pse.tax = tax
            pse.taxBase = taxBase
            pse.phone = phone
            pse.currency = currency
            pse.country = country
            pse.urlResponse = urlResponse
            pse.urlConfirmation = urlConfirmation
            pse.ip = ip
            pse.extra1 = extra1
            pse.extra2 = extra2
            pse.extra3 = extra3
            pse.city = city
            pse.depto = depto
            pse.address = address

            epayco.createPseTransaction(pse, object : EpaycoCallback {
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