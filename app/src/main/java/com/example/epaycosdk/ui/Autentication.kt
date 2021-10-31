package com.example.epaycosdk.ui

import android.app.Service
import android.content.Intent
import android.os.IBinder
import co.epayco.android.models.Authentication

class Autentication : Service() {

    override fun onBind(intent: Intent): IBinder {
        val auth = Authentication()

        auth.setApiKey("32c8ef12cc65878db1ccff30cdaf8e49")
        auth.setPrivateKey("d84e9885d4f7de545e09736e9c5beb61")
        auth.setLang("ES")
        auth.setTest(true)
//        return auth
        TODO("Return the communication channel to the service.")
    }
}