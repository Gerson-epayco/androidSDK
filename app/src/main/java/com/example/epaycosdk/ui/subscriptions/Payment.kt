package com.example.epaycosdk.ui.subscriptions

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
import co.epayco.android.models.Cash
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.databinding.PaymentFragmentBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import co.epayco.android.models.Charge




class Payment : PrincipalFragment() {
    private lateinit var viewModel: PaymentViewModel
    private var _binding: PaymentFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(PaymentViewModel::class.java)
        val epayco = Epayco(auth)

        _binding = PaymentFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val tokenCardField = binding.EditTextTokenId as EditText
            val tokenCard = tokenCardField.text.toString()

            val customerIdField = binding.EditTextCustomerId as EditText
            val customerId = customerIdField.text.toString()

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

            val duesField = binding.EditTextDues as EditText
            val dues = duesField.text.toString()

            val DefaultCardField = binding.EditTextDefaultCard as EditText
            val use_default_card_customer = DefaultCardField.text.toString()

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

            val charge = Charge()

            charge.tokenCard = tokenCard
            charge.customerId = customerId

            charge.docType = docType
            charge.docNumber = docNumber
            charge.name = name
            charge.lastName = lastName
            charge.email = email
            charge.invoice = invoice
            charge.description = description
            charge.value = value
            charge.tax = tax
            charge.taxBase = taxBase
            charge.currency = currency
            charge.dues = dues
            charge.ip = ip
            charge.use_default_card_customer = use_default_card_customer.toBoolean()
            charge.urlResponse = urlResponse
            charge.urlConfirmation = urlConfirmation
            charge.extra1 = extra1
            charge.extra2 = extra2
            charge.extra3 = extra3
            charge.city = city
            charge.departament = depto
            charge.country = country
            charge.address = address
            charge.splitpayment = splitpayment
            charge.split_app_id = split_app_id
            charge.split_merchant_id = split_merchant_id
            charge.split_type = split_type
            charge.split_primary_receiver=  split_primary_receiver
            charge.split_primary_receiver_fee = split_primary_receiver_fee

            epayco.createCharge(charge, object : EpaycoCallback {
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