package com.micro.mysegmentdefault.view.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * author : micro_hx <p>
 * desc : 手机可以一键删除的span 用于评论回复中 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/21 - 16:09 <p>
 * interface :
 */

public class DeleteableSpan extends ReplacementSpan {

    private int itemCharacterLength ;
    private int defaultColor = Color.parseColor("#009A61");

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        this.itemCharacterLength = (int) paint.measureText("m");
        return (int) (paint.measureText(text , start , end ) + 2 * this.itemCharacterLength);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int i = paint.getColor();
        paint.setColor(defaultColor);
        canvas.drawText(text,start,end,x+this.itemCharacterLength,y,paint);
    }

}
