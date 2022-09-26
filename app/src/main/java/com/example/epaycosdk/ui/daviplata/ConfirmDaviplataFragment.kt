package com.example.epaycosdk.ui.daviplata

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
import co.epayco.android.models.DaviplataConfirm
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.databinding.ConfirmDaviplataFragmentBinding
import org.json.JSONException
import org.json.JSONObject

class ConfirmDaviplataFragment : PrincipalFragment() {


    private lateinit var viewModel: ConfirmDaviplataViewModel
    private var _binding: ConfirmDaviplataFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(ConfirmDaviplataViewModel::class.java)
        val epayco = Epayco(auth)

        _binding = ConfirmDaviplataFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {


            val refPaycoField = binding.EditTextRefPayco 
            val refPayco = refPaycoField.text.toString()

            val idSessionTokenField = binding.EditTextIdSessionToken 
            val idSessionToken = idSessionTokenField.text.toString()

            val otpField = binding.EditTextOtp 
            val otp = otpField.text.toString()


            val daviplata = DaviplataConfirm()

            daviplata.refPayco = refPayco
            daviplata.idSessionToken = idSessionToken
            daviplata.otp = otp

            textView.text = "Conectando con epayco.."
            epayco.confirmDaviplata(daviplata, object : EpaycoCallback {
                @Throws(JSONException::class)
                override fun onSuccess(data: JSONObject) {
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