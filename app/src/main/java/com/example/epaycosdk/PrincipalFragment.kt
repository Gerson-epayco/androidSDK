package com.example.epaycosdk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.epayco.android.models.Authentication

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrincipalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class PrincipalFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var auth = Authentication()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        // auth = Authentication()

        auth.setApiKey("c3730256d895667d8aafc6d8778973e1")
        auth.setPrivateKey("eaf63f9490370b9eb71818ea17f510cb")
        auth.setLang("ES")
        auth.setTest(false)
    }
}