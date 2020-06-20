package com.example.rxandroidexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_debounce.*
import java.util.concurrent.TimeUnit

class DebounceActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val publishSubject = PublishSubject.create<String>()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_debounce)
//        et_input.doOnTextChanged { text, _, _, _ ->
//            publishSubject.onNext(text.toString())
//        }
//
//        publishSubject.debounce(1000L,TimeUnit.MILLISECONDS,Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                tv_output.text = it
//            }.addTo(compositeDisposable)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debounce)

        RxTextView.textChanges(et_input)
            .debounce(1000, TimeUnit.MILLISECONDS, Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    tv_output.text = "현재 텍스트 : $it"
                } else {
                    tv_output.text = ""
                }
            }.addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
