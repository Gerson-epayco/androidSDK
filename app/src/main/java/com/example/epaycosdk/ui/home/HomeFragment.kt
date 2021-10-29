package com.example.epaycosdk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.epayco.android.Epayco
import co.epayco.android.models.Authentication
import co.epayco.android.util.EpaycoCallback
import com.example.epaycosdk.R
import com.example.epaycosdk.databinding.FragmentHomeBinding
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val auth = Authentication()

        auth.setApiKey("32c8ef12cc65878db1ccff30cdaf8e49")
        auth.setPrivateKey("d84e9885d4f7de545e09736e9c5beb61")
        auth.setLang("ES")
        auth.setTest(true)


        val epayco = Epayco(auth)

        epayco.getBanks(object : EpaycoCallback {
            @Throws(JSONException::class)
            override fun onSuccess(data: JSONObject) {

                System.out.println(data)
                System.out.println("backs list")

                val textViewBank: TextView = binding.textHome
                textViewBank.text = data.toString();

            }

            override fun onError(error: Exception) {
                System.out.println("Error")
                System.out.println("Exception")
            }
        })

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}