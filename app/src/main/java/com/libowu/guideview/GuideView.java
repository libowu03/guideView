package com.libowu.guideview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GuideView extends View {
    private Paint paint;
    private int width, height;
    private Bitmap srcBm, dstBm;
    private PorterDuffXfermode porterDuffXfermode;
    private Rect rect;
    private List<GuideBean> guideBeans;
    private GuideBean guideBean;

    public GuideView(Context context){
        this(context,null);
    }
    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint = new Paint();
        paint.setColor(Color.RED);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        rect = new Rect(0,0,0,0);
        guideBeans = new ArrayList<>();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right - left;
        height = bottom - top;
        srcBm = createSrcBitmap(width, height,rect);
        dstBm = createDstBitmap(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*if (guideBeans.size() == 0){
            return;
        }*/
       /* if (guideBean == null){
            return;
        }
        int layerID = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(createSrcBitmap(width,height,guideBean.getRect()), 0, 0, paint);
        paint.setXfermode(null);
        canvas.drawBitmap(createSrcBitmap(width,height,rect),guideBean.getRect().left,guideBean.getRect().top,paint);
        canvas.restoreToCount(layerID);
        canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);*/
        if (guideBean == null){
            return;
        }
        canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
        if (guideBean.getViewBitmap() != null){
            Log.e("日志","图片大小为："+guideBean.getViewBitmap().getHeight());
            canvas.drawBitmap(guideBean.getViewBitmap(),guideBean.getRect().left,guideBean.getRect().top,paint);
        }
        int centerLine = guideBean.getRect().left+(guideBean.getRect().right-guideBean.getRect().left)/2;
        int targetCenter = centerLine - guideBean.getBitmap().getWidth()/2;
        canvas.drawBitmap(guideBean.getBitmap(),targetCenter,guideBean.getRect().bottom,paint);

    }

    public Bitmap createDstBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint dstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstPaint.setColor(Color.parseColor(/*"#99000000"*/"#99000000"));
        canvas.drawRect(0,0,getWidth(),getHeight(), dstPaint);
        return bitmap;
    }

    public void setGuideBeans(List<GuideBean> guideBeans){
        this.guideBeans = guideBeans;
        invalidate();
    }

    public Bitmap createSrcBitmap(int width, int height,Rect rect) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint scrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //scrPaint.setColor(Color.parseColor("#ec6941"));
        canvas.drawRect(rect, scrPaint);
        return bitmap;
    }

    public void setRec(GuideBean guideBean){
       this.guideBean = guideBean;
        invalidate();
    }
}
