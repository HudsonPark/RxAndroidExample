package com.example.rxandroidexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class BehaviorSubjectActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_subject)
        RxBus.subscribe<SampleRxBusEvent> {
            Log.d("bus log",it.text)
        }.addTo(compositeDisposable)
    }

    fun goToSenderActivity(view : View){
        startActivity(Intent(this, SenderActivity::class.java))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}