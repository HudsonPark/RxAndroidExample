package com.example.rxandroidexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun debounce(view : View) {
        startActivity(Intent(this,DebounceActivity::class.java))
    }

    fun throttle(view : View) {
        startActivity(Intent(this, ThrottleActivity::class.java))
    }

    fun buffer(view : View) {
        startActivity(Intent(this, BufferActivity::class.java))
    }

    fun combineLatest(view : View) {
        startActivity(Intent(this,CombineLatestActivity::class.java))
    }

    fun behaviorSubject(view : View) {
        startActivity(Intent(this,BehaviorSubjectActivity::class.java))
    }

}
