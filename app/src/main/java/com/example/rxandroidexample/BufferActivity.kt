package com.example.rxandroidexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rxandroidexample.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class BufferActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val behaviorSubject = BehaviorSubject.createDefault(0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer)
        behaviorSubject.buffer(2, 1)
            .map { it[0] to it[1] }
            .subscribe {
                if (it.second - it.first < 2000L) {
                    super.onBackPressed()
                } else {
                    Toast.makeText(this, "한 번 더 눌러주시면 화면이 종료됩니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onBackPressed() {
        behaviorSubject.onNext(System.currentTimeMillis())
    }
}
