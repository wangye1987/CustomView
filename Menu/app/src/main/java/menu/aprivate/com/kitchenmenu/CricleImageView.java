package menu.aprivate.com.kitchenmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.service.dreams.DreamService;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import static java.lang.Math.min;

/**
 * Created by wangkui on 2018/11/6.
 */

public class CricleImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint mPaint;
    private int mRadius,mRadiuss;
    private int round;
    private Bitmap mbitmapDrawable;

    public CricleImageView(Context context) {
        super(context);
    }

    public CricleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CricleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化画笔
     * */
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量的圆的大小 取最小值
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            round = getDisplay().getWidth()/3-10;
//        }
        round = Math.min(getMeasuredWidth(),getMeasuredHeight());
        //圆的半径大小
        mRadius = round /2;
        mRadiuss = round +10;
        setMeasuredDimension(round+20, round+20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Bitmap bitmap = DrawableToBitmap(getDrawable());
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix mMatrix = new Matrix();
        //计算缩放比例 按照圆的直径等比例缩放图片 必须转float
        float mScale = (float) mRadius *2.0f/ Math.min(bitmap.getHeight(), bitmap.getWidth());
        mMatrix.setScale(mScale,mScale);
        //设置缩放比
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        //画背景
        canvas.drawCircle(mRadius+10,mRadius+10,mRadius+5,paint);
        //画图形
        canvas.drawCircle(mRadius+10,mRadius+10,mRadius,mPaint);

    }


    private Bitmap DrawableToBitmap(Drawable mDrawable){
        if(mDrawable instanceof BitmapDrawable){
                BitmapDrawable bitmapDrawable = (BitmapDrawable) mDrawable;
                mbitmapDrawable = bitmapDrawable.getBitmap();
                return mbitmapDrawable;
        }
        //返回图片高宽单位是dp
        int width =  mDrawable.getIntrinsicWidth();
        int height = mDrawable.getIntrinsicHeight();
        Bitmap mBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(mBitmap);
//        mDrawable.setBounds(0, 0, width, height);
//        mDrawable.draw(canvas);
        return mBitmap;
    }
}
