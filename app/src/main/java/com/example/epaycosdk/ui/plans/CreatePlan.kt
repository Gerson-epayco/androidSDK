package com.example.epaycosdk.ui.plans

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
import com.example.epaycosdk.databinding.CreatePlanFragmentBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import co.epayco.android.models.Plan




class CreatePlan : PrincipalFragment() {

    private lateinit var viewModel: CreatePlanViewModel
    private var _binding: CreatePlanFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(CreatePlanViewModel::class.java)
        val epayco = Epayco(auth)
        val plan = Plan()

        _binding = CreatePlanFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val idPlanField = binding.EditTextIdPlan 
            val idPlan = idPlanField.text.toString()

            val nameField = binding.EditTextName 
            val name = nameField.text.toString()

            val descriptionField = binding.EditTextDescription 
            val description = descriptionField.text.toString()

            val amountField = binding.EditTextAmount 
            val amount = amountField.text.toString()

            val currencyField = binding.EditTextCurrency 
            val currency = currencyField.text.toString()

            val intervalField = binding.EditTextInterval 
            val interval = intervalField.text.toString()

            val intervalCountField = binding.EditTextIntervalCount 
            val intervalCount = intervalCountField.text.toString()

            val trialDaysField = binding.EditTextTrialDays 
            val trialDays = trialDaysField.text.toString()

            plan.idPlan = idPlan
            plan.name = name
            plan.description = description
            plan.amount = amount
            plan.currency = currency
            plan.interval = interval
            plan.intervalCount = intervalCount
            plan.trialDays = trialDays

            epayco.createPlan(plan, object : EpaycoCallback {
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