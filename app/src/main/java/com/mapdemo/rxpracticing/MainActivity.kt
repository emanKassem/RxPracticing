package com.mapdemo.rxpracticing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    companion object
    {
        val TAG = "MainActivity"
    }

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
        zipOperator()
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

    }
}