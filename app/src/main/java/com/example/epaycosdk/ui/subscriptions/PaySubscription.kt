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
import com.example.epaycosdk.databinding.PaySubscriptionFragmentBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import co.epayco.android.models.ChargeSub




class PaySubscription : PrincipalFragment() {



    private lateinit var viewModel: PaySubscriptionViewModel
    private var _binding: PaySubscriptionFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(PaySubscriptionViewModel::class.java)
        val epayco = Epayco(auth)
        val subscription = Subscription()

        _binding = PaySubscriptionFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            val tokenIDField = binding.EditTextTokenId as EditText
            val tokenID = tokenIDField.text.toString()

            val customerIDField = binding.EditTextCustomerId as EditText
            val customerID = customerIDField.text.toString()

            val idPlanField = binding.EditTextIdPlan as EditText
            val idPlan = idPlanField.text.toString()

            val docTypeField = binding.EditTextDocType as EditText
            val docType = docTypeField.text.toString()

            val docNumberField = binding.EditTextDocNumber as EditText
            val docNumber = docNumberField.text.toString()

            val ipField = binding.EditTextIP as EditText
            val ip = ipField.text.toString()

            val sub = ChargeSub()

            sub.idPlan = idPlan
            sub.customer = customerID
            sub.tokenCard = tokenID
            sub.docType = docType
            sub.docNumber = docNumber
            sub.ip = ip /*This is the client's IP, it is required*/

            epayco.chargeSubscription(sub, object : EpaycoCallback {
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