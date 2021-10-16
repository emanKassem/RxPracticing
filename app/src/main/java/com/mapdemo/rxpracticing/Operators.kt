package com.mapdemo.rxpracticing

import android.util.Log
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.GroupedObservable
import java.lang.Exception
import java.util.concurrent.TimeUnit

val items = mutableListOf(1,2,3,4,5,6,1,2,3,4,5,6)
val items2 = mutableListOf(1,2,3,4,5,6,7,8,9,10,1,11)
val users = mutableListOf("A","B","C","D","E","F")

fun justOperator() {
    val observable = Observable.just(items)
    val observer = object : Observer<List<Int>> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe: ")
        }

        override fun onNext(t: List<Int>?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(MainActivity.TAG, "onError: $e")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete: ")
        }
    }

    observable.subscribe(observer)
}
fun fromOperator(){
    val observable = Observable.fromArray(items, items2)
    val observer = object : Observer<List<Int>>{
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe: ")
        }

        override fun onNext(t: List<Int>?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(MainActivity.TAG, "onError: $e")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete: ")
        }
    }

    observable.subscribe(observer)
}
fun fromIterableOperator(){
    val observable = Observable.fromIterable(items)
    val observer = object : Observer<Int>{
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe: ")
        }

        override fun onNext(t: Int?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(MainActivity.TAG, "onError: $e")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete: ")
        }
    }

    observable.subscribe(observer)
}
fun rangeOperator(): Observable<Int> = Observable.range(1, 10)
fun repeatOperator(): Observable<Int> = Observable.range(1, 10).repeat(2)
fun intervalOperator(): Observable<Long> = Observable.interval(1, TimeUnit.SECONDS).takeWhile{value->
    value<=10
}
fun timerOperator(): Observable<Long> = Observable.timer(1, TimeUnit.SECONDS)
fun createOperator() : Observable<Int> = Observable.create{ emitter->
    try {
        for (item in items)
            emitter.onNext(item)
        emitter.onComplete()
    }catch (e: Exception)
    {
       emitter.onError(e)
    }

}
fun filterOperator(): Observable<Int> = Observable.fromIterable(items).filter { it%2!=0 }
fun lastOperator(): Single<Int> = Observable.fromIterable(items).last(-1)
fun lastElementOperator(): Maybe<Int> = Observable.fromIterable(items).lastElement()
fun lastOrErrorOperator(): Single<Int> = Observable.fromIterable(items).lastOrError()
fun distinctOperator() :Observable<Int> = Observable.fromIterable(items).distinct()
fun skipOperator() : Observable<Int> = Observable.fromIterable(items).skip(5)
fun skipLastOperator() : Observable<Int> = Observable.fromIterable(items).skipLast(5)
fun skipTimeOperator() : Observable<Int> = Observable.fromIterable(items).skipLast(2, TimeUnit.MILLISECONDS)
fun bufferOperator(): Observable<List<Int>> = Observable.fromIterable(items).buffer(3)
fun mapOperator(): Observable<Int> = Observable.fromIterable(items).map { it*it }
fun flatMapOperator() :Observable<Int> = Observable.fromIterable(items).flatMap { item ->
    Observable.just(item).filter{it%2!=0}
}
fun groupByOperator(): Observable<GroupedObservable<Boolean, Int>> =
        Observable.fromIterable(items).groupBy { it%2==0 }

fun getNumFrom0To100(): Observable<Int> = Observable.range(1, 100)
fun getNumFrom101To150(): Observable<Int> = Observable.range(101, 50)

fun mergeOperator(): Observable<Int> = Observable.merge(getNumFrom0To100(), getNumFrom101To150())
fun concatOperator() : Observable<Int> = getNumFrom0To100().concatWith(getNumFrom101To150())
fun startWithOperator(): Observable<Int> = getNumFrom101To150().startWith(getNumFrom0To100())
fun zipOperator(): Observable<String> = Observable.zip(
        Observable.fromIterable(items),
        Observable.fromIterable(users),
        {id, user-> "$id : $user" }
)