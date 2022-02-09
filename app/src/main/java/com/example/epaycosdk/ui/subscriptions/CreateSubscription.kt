package com.example.epaycosdk.ui.subscriptions

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
import co.epayco.android.models.Subscription
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.R
import com.example.epaycosdk.databinding.CreateSubscriptionFragmentBinding
import com.example.epaycosdk.ui.plans.PlanDetailViewModel
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class CreateSubscription : PrincipalFragment() {


    private lateinit var viewModel: CreateSubscriptionViewModel
    private var _binding: CreateSubscriptionFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(CreateSubscriptionViewModel::class.java)
        val epayco = Epayco(auth)
        val subscription = Subscription()

        _binding = CreateSubscriptionFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val tokenIDField = binding.EditTextTokenId 
            val tokenID = tokenIDField.text.toString()

            val customerIDField = binding.EditTextCustomerId 
            val customerID = customerIDField.text.toString()

            val idPlanField = binding.EditTextIdPlan 
            val idPlan = idPlanField.text.toString()

            val docTypeField = binding.EditTextDocType 
            val docType = docTypeField.text.toString()

            val docNumberField = binding.EditTextDocNumber 
            val docNumber = docNumberField.text.toString()

            val urlConfirmationField = binding.EditTextUrlConfirmation 
            val urlConfirmation = urlConfirmationField.text.toString()

            subscription.setTokenCard(tokenID)
            subscription.setCustomer(customerID)
            subscription.setIdPlan(idPlan)
            subscription.setDoc_type(docType)
            subscription.setDoc_number(docNumber)
            subscription.setUrlConfirmation(urlConfirmation)

            epayco.createSubscription(subscription, object : EpaycoCallback {
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