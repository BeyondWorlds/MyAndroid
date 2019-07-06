package com.wq.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wq.allandroid.R;


/**
 * Created by Administrator on 2017/6/9.
 */

public class SampleView extends View {
    private String mSampleContent;
    private int mSampleColor;
    private int mSampleSize;

    private Paint mPaint;
    private Rect mBound;

    public SampleView(Context context) {
        this(context, null);
    }

    public SampleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 获得自定义的属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们自定义的样式属性
         */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.sample, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.sample_customContext:
                    mSampleContent = typedArray.getString(attr);
                    break;
                case R.styleable.sample_customColor:
                    mSampleColor = typedArray.getInt(attr, Color.BLUE);
                    break;
                case R.styleable.sample_customSize:
                    mSampleSize = typedArray.getDimensionPixelSize(attr, 20);
                    break;
            }
        }
        typedArray.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mSampleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mSampleContent, 0, mSampleContent.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mSampleColor);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mSampleColor);
        canvas.drawText(mSampleContent, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
