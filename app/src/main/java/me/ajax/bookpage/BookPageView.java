package me.ajax.bookpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by aj on 2018/4/2
 */

public class BookPageView extends View {

    Paint mPaint = new Paint();
    Paint linePaint = new Paint();
    Path path = new Path();

    PointF a = new PointF();
    PointF f = new PointF();
    PointF g = new PointF();

    PointF e = new PointF();
    PointF h = new PointF();

    PointF c = new PointF();
    PointF j = new PointF();

    PointF b = new PointF();
    PointF k = new PointF();

    PointF d = new PointF();
    PointF i = new PointF();

    public BookPageView(Context context) {
        super(context);
        init();
    }

    public BookPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {


        //画笔
        linePaint.setColor(0xFFFF00FF);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFFFF0000);
        mPaint.setTextSize(dp2Dx(18));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int mWidth = getWidth();
        int mHeight = getHeight();

        //拖动点
        a.set(mWidth / 2, mHeight / 2);

        //角
        f.set(mWidth, mHeight);

        //a、f 中点
        g.set((a.x + f.x) / 2, (a.y + f.y) / 2);

        //em=gm*gm/mf;
        float em = ((f.x - g.x) * (f.x - g.x)) / (f.x - g.x);
        e.set(g.x - em, f.y);
        //hn=gn*gn/nf;
        float hn = ((f.y - g.y) * (f.y - g.y)) / (f.y - g.y);
        h.set(f.x, g.y - hn);

        // cx=ex- ef/2 ;
        c.set(e.x - (f.x - e.x) / 2, f.y);
        j.set(f.x, h.y - (f.y - h.y) / 2);

        b.set(GeometryUtils.intersectionX(a, e, c, j), GeometryUtils.intersectionY(a, e, c, j));
        k.set(GeometryUtils.intersectionX(a, h, c, j), GeometryUtils.intersectionY(a, h, c, j));

        d.set((b.x + e.x) / 2, (b.y + e.y) / 2);
        i.set((k.x + h.x) / 2, (k.y + h.y) / 2);

        canvas.drawText("a", a.x, a.y, mPaint);
        canvas.drawText("b", b.x, b.y, mPaint);
        canvas.drawText("k", k.x, k.y, mPaint);
        canvas.drawText("c", c.x, c.y, mPaint);
        canvas.drawText("d", d.x, d.y, mPaint);
        canvas.drawText("e", e.x, e.y, mPaint);
        canvas.drawText("f", f.x - dp2Dx(10), f.y - dp2Dx(10), mPaint);
        canvas.drawText("g", g.x, g.y, mPaint);
        canvas.drawText("h", h.x - dp2Dx(10), h.y, mPaint);
        canvas.drawText("i", i.x, i.y, mPaint);
        canvas.drawText("j", j.x - dp2Dx(10), j.y - dp2Dx(10), mPaint);

        canvas.drawLine(a.x, a.y, f.x, f.y, linePaint);
        canvas.drawLine(e.x, e.y, h.x, h.y, linePaint);
        canvas.drawLine(c.x, c.y, j.x, j.y, linePaint);
        canvas.drawLine(e.x, e.y, a.x, a.y, linePaint);
        canvas.drawLine(h.x, h.y, a.x, a.y, linePaint);
        canvas.drawLine(d.x, d.y, i.x, i.y, linePaint);

        path.moveTo(b.x, b.y);
        path.quadTo(d.x, d.y, c.x, c.y);
        path.moveTo(k.x,k.y);
        path.quadTo(i.x, i.y, j.x, j.y);
        canvas.drawPath(path, linePaint);
    }

    int dp2Dx(int dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

    void l(Object o) {
        Log.e("######", o.toString());
    }
}
