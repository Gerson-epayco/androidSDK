package com.example.epaycosdk.ui

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
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import com.example.epaycosdk.databinding.FragmentHomeBinding
import com.example.epaycosdk.ui.home.HomeViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerListFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val auth = autentication()
        val epayco = Epayco(auth)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        epayco.getCustomerList(object : EpaycoCallback {
            @Throws(JSONException::class)
            override fun onSuccess(data: JSONObject) {
                System.out.println("Result customer: ")
                System.out.println(data)

                val textViewBank: TextView =  binding.textHome
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

    private fun autentication(): Authentication {
        val auth = Authentication()

        auth.setApiKey("32c8ef12cc65878db1ccff30cdaf8e49")
        auth.setPrivateKey("d84e9885d4f7de545e09736e9c5beb61")
        auth.setLang("ES")
        auth.setTest(true)
        return auth
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomerListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomerListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}