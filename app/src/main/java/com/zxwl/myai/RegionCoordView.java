package com.zxwl.myai;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by sks on 2018/5/8.
 */

public class RegionCoordView extends android.support.v7.widget.AppCompatImageView {

    private Context mContext;

    public RegionCoordView(Context context) {
        this(context, null);
    }

    public RegionCoordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            int area = 0;
            if (x >= 0 && x < 200 && y > 0 && y < 200) {
                area = 1;
            } else if (x > 200 && x < 400 && y > 0 && y < 200) {
                area = 2;
            } else if (x > 0 && x < 200 && y > 200 && y < 400) {
                area = 3;
            } else if (x > 200 && x < 400 && y > 200 && y < 400) {
                area = 4;
            }
            ((MainActivity) mContext).showClickArea(area);
        }
        return super.onTouchEvent(event);
    }
}
