package com.zeropercenthappy.zphandroidutilssample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zeropercenthappy.utilslibrary.utils.ZPHLogger

class MainActivity : AppCompatActivity(), ZPHLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
