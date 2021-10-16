package com.mapdemo.rxpracticing

import android.util.Log
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception


fun observable() : Observable<Int> = Observable.create { emitter->
    for (item in 1 .. 10)
        emitter.onNext(item)
    emitter.onComplete()
}

fun observer() : Observer<Int> = object :Observer<Int>{
    override fun onSubscribe(d: Disposable?) {
        Log.d(MainActivity.TAG, "onSubscribe")
    }

    override fun onNext(t: Int?) {
        Log.d(MainActivity.TAG, "OnNext: $t")
    }

    override fun onError(e: Throwable?) {
        Log.d(MainActivity.TAG, "OnError: $e")
    }

    override fun onComplete() {
        Log.d(MainActivity.TAG, "OnComplete")
    }
}

fun singleObservable() : Single<String> = Single.create{emitter->
    emitter.onSuccess("Single Observable")
}

fun singleObserver() : SingleObserver<String> = object : SingleObserver<String>{
    override fun onSubscribe(d: Disposable?) {

    }

    override fun onSuccess(t: String?) {
        Log.d(MainActivity.TAG, "OnSuccess: $t")
    }

    override fun onError(e: Throwable?) {

    }
}

fun maybeObservable() : Maybe<List<String>> = Maybe.just(emptyList())

fun maybeObserver() : MaybeObserver<List<String>> = object : MaybeObserver<List<String>>{
    override fun onSubscribe(d: Disposable?) {

    }

    override fun onSuccess(t: List<String>?) {
        Log.d(MainActivity.TAG, "OnSuccess: $t")
    }

    override fun onError(e: Throwable?) {

    }

    override fun onComplete() {
        Log.d(MainActivity.TAG, "OnComplete")
    }
}

fun completableObservable(): Completable = Completable.create{emitter->
    if (!emitter.isDisposed)
        getLocationLocation()
    emitter.onComplete()
}

fun getLocationLocation() {
    //throw Exception("Ex")
    Log.d(MainActivity.TAG, "lat=22.4, lng=24.8")
}

fun completableObserver(): CompletableObserver = object : CompletableObserver{
    override fun onSubscribe(d: Disposable?) {

    }

    override fun onComplete() {
        Log.d(MainActivity.TAG, "OnComplete")
    }

    override fun onError(e: Throwable?) {
        Log.d(MainActivity.TAG, "OnError: $e")
    }
}

fun flowable() : Flowable<Int> = Flowable.range(1, 100).onBackpressureBuffer()
