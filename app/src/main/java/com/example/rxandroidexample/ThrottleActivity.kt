package com.example.rxandroidexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_throttle.*
import java.util.concurrent.TimeUnit

class ThrottleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private var num = 0
    private val behaviorSubject = BehaviorSubject.createDefault(Unit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_throttle)
        behaviorSubject.throttleFirst(2000L,TimeUnit.MILLISECONDS)
            .subscribe {
                num++
                tv_output.text = num.toString()
            }.addTo(compositeDisposable)
    }

    fun increase(view: View) {
        behaviorSubject.onNext(Unit)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
