package com.example.rxandroidexample

import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.BehaviorSubject

object RxBus {

    val behaviorSubject : BehaviorSubject<RxEvent> = BehaviorSubject.create()

    fun onNext(event: RxEvent) {
        behaviorSubject.onNext(event)
    }

    inline fun <reified T : RxEvent> subscribe(crossinline onNext: (T) -> Unit) =
        behaviorSubject.ofType<T>()
            .subscribe { onNext(it) }
}