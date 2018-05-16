package com.zeropercenthappy.zphandroidutilssample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zeropercenthappy.utilslibrary.DateUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateUtils.INSTANCE.getFirstDayOfWeekByYearMonth(2018, 4);
    }
}
