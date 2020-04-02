package com.example.bigbrothermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var hubConnection: HubConnection
     var stanislaw : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!stanislaw)
        hubConnection = HubConnectionBuilder.create("http://10.0.2.2:5000/testhub").build()

        myButton.setOnClickListener {

            if(hubConnection.connectionState  == HubConnectionState.DISCONNECTED)
            {
                stanislaw = true

                hubConnection.start().blockingAwait()
            }

            if(hubConnection.connectionState == HubConnectionState.CONNECTED)
            {
                hubConnection.send("TestMessage", "siema")
            }

        }
    }
}
