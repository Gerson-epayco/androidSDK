package com.example.epaycosdk.ui.cash

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
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.databinding.CreateCashFragmentBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import co.epayco.android.models.Cash




class CreateCash : PrincipalFragment() {

    private lateinit var viewModel: CreateCashViewModel
    private var _binding: CreateCashFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(CreateCashViewModel::class.java)
        val epayco = Epayco(auth)

        _binding = CreateCashFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val typeField = binding.EditTextType as EditText
            val type = typeField.text.toString()

            val endDateField = binding.EditTextEndDate as EditText
            val endDate = endDateField.text.toString()

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

            val splitpaymentField = binding.EditTextSplitpayment as EditText
            val splitpayment = splitpaymentField.text.toString()

            val split_app_idField = binding.EditTextAddressSplitAppId as EditText
            val split_app_id = split_app_idField.text.toString()

            val split_merchant_idField = binding.EditTextSplitMerchantId as EditText
            val split_merchant_id = split_merchant_idField.text.toString()

            val split_typeField = binding.EditTextSplitType as EditText
            val split_type = split_typeField.text.toString()

            val split_primary_receiversField = binding.EditTextSplitPrimaryReceiver as EditText
            val split_primary_receiver = split_primary_receiversField.text.toString()

            val split_primary_receiver_feeField = binding.EditTextSplitPrimaryReceiverFee as EditText
            val split_primary_receiver_fee = split_primary_receiver_feeField.text.toString()

            val cash = Cash()

            cash.type = type
            cash.endDate = endDate
            cash.docType = docType
            cash.docNumber = docNumber
            cash.name = name
            cash.lastName = lastName
            cash.email = email
            cash.invoice = invoice
            cash.description = description
            cash.value = value
            cash.tax = tax
            cash.taxBase = taxBase
            cash.ico = ico
            cash.phone = phone
            cash.currency = currency
            cash.ip = ip
            cash.urlResponse = urlResponse
            cash.urlConfirmation = urlConfirmation
            cash.extra1 = extra1
            cash.extra2 = extra2
            cash.extra3 = extra3
            cash.country = country
            cash.city = city
            cash.depto = depto
            cash.address = address
            cash.splitpayment = splitpayment
            cash.split_app_id = split_app_id
            cash.split_merchant_id = split_merchant_id
            cash.split_type = split_type
            cash.split_primary_receiver=  split_primary_receiver
            cash.split_primary_receiver_fee = split_primary_receiver_fee


            epayco.createCashTransaction(cash, object : EpaycoCallback {
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