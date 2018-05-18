package com.zxwl.myai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float f = event.getY();

        Log.e("TTTTT",x+"-----"+f);

        return super.onTouchEvent(event);
    }
}
