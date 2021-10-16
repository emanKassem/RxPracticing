package com.mapdemo.rxpracticing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    companion object
    {
        const val TAG = "MainActivity"
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*groupByOperator().subscribe(
                {group->
                    group.subscribe{
                        Log.d(TAG, "OnNext: Group${group.key}: Value: $it")
                    }
                },
                {
                    Log.d(TAG, "OnError: $it")
                },
                {
                    Log.d(TAG, "OnComplete")
                }
        )*/

        /*groupByOperator()
                .flatMapSingle {group->
                    group.toList()
                }.subscribe(
                        {
                            Log.d(TAG, "OnNext: $it")
                        },
                        {
                            Log.d(TAG, "OnError: $it")
                        },
                        {
                            Log.d(TAG, "OnComplete")
                        }
                )
*/
        /*zipOperator()
                .subscribe(
                        {
                            Log.d(TAG, "OnNext: $it")
                        },
                        {
                            Log.d(TAG, "OnError: $it")
                        },
                        {
                            Log.d(TAG, "OnComplete")
                        }
                )*/

        //observable().subscribe(observer())
        //singleObservable().subscribe(singleObserver())
        //maybeObservable().subscribe(maybeObserver())
        //completableObservable().subscribe(completableObserver())
       /* compositeDisposable.add(
                observable().toFlowable(BackpressureStrategy.BUFFER)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    Log.d(TAG, "OnNext: $it")
                                },
                                {
                                    Log.d(TAG, "OnError: $it")
                                },
                                {
                                    Log.d(TAG, "OnComplete")
                                }
                        )
        )*/
        compositeDisposable.add(
                observable().toFlowable(BackpressureStrategy.BUFFER)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    findViewById<TextView>(R.id.text2).text = it.toString()
                                    Log.d("TEST","Thread ${Thread.currentThread().name}")
                                    Log.d("TEST", "OnNext: $it")
                                },
                                {
                                    Log.d("TEST", "OnError: $it")
                                },
                                {
                                    Log.d("TEST", "OnComplete")
                                }
                        )
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        Log.d(TAG, "onDestroy:")
        super.onDestroy()
    }
}
