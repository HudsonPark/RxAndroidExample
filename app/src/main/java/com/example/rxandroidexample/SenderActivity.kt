package com.example.rxandroidexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SenderActivity : AppCompatActivity(){

    private lateinit var etInput : EditText
    private lateinit var btnSend : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)
        etInput = findViewById(R.id.et_input)
        btnSend = findViewById(R.id.btn_send)

        btnSend.setOnClickListener {
            Log.d("bus log","send() called")
            val text = etInput.text.toString()
            RxBus.onNext(SampleRxBusEvent(text))
        }
    }
}
