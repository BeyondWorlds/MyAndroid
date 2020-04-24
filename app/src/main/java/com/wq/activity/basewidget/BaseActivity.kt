package com.wq.activity.basewidget

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity(){
    companion object{
        var TAG=BaseActivity.javaClass.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}