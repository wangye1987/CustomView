package menu.aprivate.com.kitchenmenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by wangkui on 2018/11/8.
 */

public class LoadView extends View {

    private Paint mPaint;

    public LoadView(Context context) {
        super(context);
        initData();
    }

    public LoadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public LoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (startAngle >= 360) {
                    startAngle =0;
                }else{
                    startAngle++;
                }
                postInvalidate();
            }
        }, 100, 2);// 每隔20毫秒执行一次
    }

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };
    float sweepAngle = 10;
    float startAngle = -90;
    float endAngle = 45;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radiusX = getWidth() / 2;
        int radiusY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3;
        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setAlpha(50);
//        mPaint.setColor(getResources().getColor(R.color.colorRound3));
        canvas.drawCircle(radiusX, radiusY, radius, mPaint);
        // 文字宽
        float textWidth = mPaint.measureText("香");
        // 文字baseline在y轴方向的位置
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(40);
        float baseLineY = Math.abs(mPaint.ascent() + mPaint.descent()) / 2;
//        canvas.drawText("香", -textWidth / 2+radiusX, baseLineY+radiusY, mPaint);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        RectF rf = new RectF(radiusX - radius, radiusY - radius, radiusX + radius, radiusY + radius);
        mPaint.setAlpha(100);
        canvas.drawArc(rf, startAngle, endAngle, false, mPaint);
        System.out.println("start="+startAngle+" ----and"+endAngle);

    }
}
