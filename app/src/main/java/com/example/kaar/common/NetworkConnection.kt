package com.example.kaar.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build
import androidx.lifecycle.LiveData
import javax.inject.Inject

@Suppress("DEPRECATION")

class NetworkConnection @Inject constructor(private val context: Context) : LiveData<Boolean>() {


    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var networkConnectionCallback: ConnectivityManager.NetworkCallback


    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        networkConnectionCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                postValue(true)
            }
        }
        return networkConnectionCallback
    }

    private fun updateNetworkConnection() {
        val activeNetworkConnection: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetworkConnection?.isConnected == true)
    }

    private val networkReceiver= object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateNetworkConnection()
        }
    }

    override fun onActive() {
        super.onActive()
        updateNetworkConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
            }
            else -> {
                context.registerReceiver(
                    networkReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (this::networkConnectionCallback.isInitialized) {
            connectivityManager.unregisterNetworkCallback(networkConnectionCallback)
        }
//        super.onInactive()
//        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
    }




}
