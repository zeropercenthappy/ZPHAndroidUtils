package com.zeropercenthappy.zphandroidutilssample

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.zeropercenthappy.utilslibrary.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.setOnClickListener {
            val apkFile = File("${Environment.getExternalStorageDirectory().absolutePath}/app-debug.apk")
            AppUtils.installAPk(this, apkFile)
        }
    }
}
