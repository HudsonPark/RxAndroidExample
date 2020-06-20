package com.example.rxandroidexample

interface RxEvent

data class SampleRxBusEvent(val text : String) : RxEvent
