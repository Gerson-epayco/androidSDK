package com.example.epaycosdk.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.epayco.android.Epayco
import co.epayco.android.models.Authentication
import co.epayco.android.models.Client
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.PrincipalFragment
import com.example.epaycosdk.R
import com.example.epaycosdk.databinding.FragmentCreateCustomerBinding
import com.example.epaycosdk.ui.slideshow.SlideshowViewModel
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateCustomerFragment : PrincipalFragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentCreateCustomerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        val epayco = Epayco(auth)
        val client = Client()

        _binding = FragmentCreateCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var btn_submit: Button = binding.ButtonSendFeedback

        btn_submit.setOnClickListener {

            System.out.println("click")
            val tokenIdField = binding.EditTextTokenId 
            val tokenId = tokenIdField.text.toString()

            val nameField = binding.EditTextName 
            val name = nameField.text.toString()

            val emailField = binding.EditTextEmail 
            val email = emailField.text.toString()

            val phoneField = binding.EditTextPhone 
            val phone = phoneField.text.toString()

            val defaultCardField = binding.EditTextDefault 
            val defaultCard = defaultCardField.text.toString()

            client.setTokenId(tokenId)
            client.setName(name)
            client.setEmail(email)
            client.setPhone(phone)
            client.setDefaultCard(defaultCard.toBoolean());


            epayco.createCustomer(client, object : EpaycoCallback {
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

    fun sendFeedback(button: View?) {
        // Do click handling here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}