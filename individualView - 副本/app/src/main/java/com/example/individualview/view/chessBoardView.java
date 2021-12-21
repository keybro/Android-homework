package com.example.individualview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.individualview.R;


public class chessBoardView extends AppCompatTextView {

    /**
     * 全局画笔
     */
    Paint m_paint;

    int m_strokeSize;
    int m_backgroundColor;


    public chessBoardView(Context context,AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChessBoardView);
        m_strokeSize = typedArray.getInteger(R.styleable.ChessBoardView_strokeSize, 30);
        m_backgroundColor = typedArray.getColor(R.styleable.ChessBoardView_backgroundColor, Color.BLACK);
        //返回以前检索到的StyledAttributes，以便以后重新使用。
        typedArray.recycle();
        init();
    }

    public chessBoardView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        m_paint.setStyle(Paint.Style.FILL);
        m_paint.setColor(m_backgroundColor);
        canvas.drawRect(0, 0, width, height, m_paint);
        m_paint.setStyle(Paint.Style.STROKE);
        m_paint.setStrokeWidth(m_strokeSize);
        //划线色与背景色不能相同
        if(m_backgroundColor == Color.WHITE)
            m_paint.setColor(Color.BLACK);
        else
            m_paint.setColor(Color.WHITE);

        int cols = 6;
        //画横线
        int size = width / cols;   // 每一个小块的大小
        int rows = height / size + 1;
        for (int i = 0; i < rows; i++) {
            int y = i * size;
            canvas.drawLine(0, y, width, y, m_paint);
        }
        // 画竖线
        for (int i = 0; i < cols; i++) {
            int x = i * size;
            canvas.drawLine(x, 0, x, height, m_paint);
        }

    }



    protected void init(){
        m_paint = new Paint();
    }




}
