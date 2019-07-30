package com.libowu.guideview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.libowu.guideview.bean.GuideBean;

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
    private int guideIndex;

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
        if (guideBeans == null || guideBeans.size() == 0){
            this.setVisibility(GONE);
            return;
        }
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
       if (Config.OPENMORE){
           canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
           for (int i=0; i<guideBeans.size(); i++){
               drawBuyView(canvas,guideBeans.get(i));
           }
       }else {
           drawBuyView(canvas,guideBean);
       }
    }


    /**
     * 绘制高亮区及说明图片
     * @param canvas
     * @param guideBean
     */
    public void drawBuyView(Canvas canvas,GuideBean guideBean){
        if (guideBean == null){
            return;
        }
        //如果是一屏显示多个控件说明，遮罩层在循环前绘制一边即可
        if (!Config.OPENMORE){
            canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
        }
        //绘制view的图像
        if (guideBean.getViewBitmap() != null){
            canvas.drawBitmap(guideBean.getViewBitmap(),guideBean.getRect().left,guideBean.getRect().top,paint);
        }
        //说明控件的中线坐标
        int centerLine = guideBean.getRect().left+(guideBean.getRect().right-guideBean.getRect().left)/2;
        //说明图片的左边距离
        int targetCenter = centerLine - guideBean.getBitmap().getWidth()/2;
        if (guideBean.getBitmap() != null){
            canvas.drawBitmap(guideBean.getBitmap(),targetCenter+guideBean.getMarginLeft(),guideBean.getRect().bottom+guideBean.getMarginTop(),paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (guideBeans == null || guideBeans.size() == 0){
                    return true;
                }

                //如果是一屏显示多个控件说明，则点击后直接隐藏view
               if (Config.OPENMORE){
                   this.setVisibility(GONE);
               }else {
                   //抬起手指时显示下一张引导，当索引值大于集合长度时，索引归零，并隐藏引导
                   guideIndex++;
                   if (guideIndex >= guideBeans.size()){
                       this.setVisibility(GONE);
                       guideIndex = 0;
                       return true;
                   }
                   guideBean = guideBeans.get(guideIndex);
                   invalidate();
               }
                break;
            default:
                break;
        }
        return true;
    }

    public void showGuide(){
        this.setVisibility(VISIBLE);
        if (guideBeans != null && guideBeans.size() !=0){
            guideBean = guideBeans.get(0);
        }
        invalidate();
    }

    /**
     * 绘制遮罩层
     * @param width
     * @param height
     * @return
     */
    public Bitmap createDstBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint dstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstPaint.setColor(Config.COLOR);
        canvas.drawRect(0,0,getWidth(),getHeight(), dstPaint);
        return bitmap;
    }

    public void setGuideBeans(List<GuideBean> guideBeans){
        this.guideBeans = guideBeans;
        invalidate();
    }

    /**
     * 绘制高亮区
     * @param width
     * @param height
     * @param rect
     * @return
     */
    public Bitmap createSrcBitmap(int width, int height,Rect rect) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint scrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRect(rect, scrPaint);
        return bitmap;
    }

    /**
     * view的配置文件，务必在调用showGuide方法前配置完guideview
     */
    public static class Config{
        /**
         * 配置遮罩层颜色
         */
        public static int COLOR = Color.parseColor("#99000000");

        /**
         * 是否打开一个界面显示多个控件说明,默认是关闭的
         */
        public static boolean OPENMORE = false;
    }
}
