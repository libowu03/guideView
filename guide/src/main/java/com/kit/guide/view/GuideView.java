package com.kit.guide.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kit.guide.R;
import com.kit.guide.bean.GuideBean;
import com.kit.guide.callBack.GuideViewClickCallBack;
import com.kit.guide.utils.GuideViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libowu
 * @date 2019/07/30
 * 需要注意的是，如果高亮区是控件本身而不是基本几何图形，则控件必须加入背景，否则控件本身的颜色是透明的，最终绘制出的高亮区会与遮罩层融为一体，这也是可以理解的，毕竟控件本身是透明的。
 * guideview的核心代码
 */
public class GuideView extends View {
    private Paint paint;
    private Paint textPaint;
    private int width, height;
    private PorterDuffXfermode porterDuffXfermode;
    private Rect rect;
    private List<GuideBean> guideBeans;
    private GuideBean guideBean;
    private int guideIndex;
    private GuideViewClickCallBack guideViewClickCallBack;
    private int maskColor;
    private boolean openMore;
    private boolean clickExact;
    private int heightLightPadding;
    private int guideTextColor;
    private float guideTextSize;
    private Activity act;

    public GuideView(Context context){
        this(context,null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint = new Paint();
        paint.setColor(Color.RED);
        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GuideView, defStyleAttr, 0);
        maskColor = attr.getColor(R.styleable.GuideView_maskColor,Color.parseColor("#99000000"));
        openMore = attr.getBoolean(R.styleable.GuideView_openMore,false);
        clickExact = attr.getBoolean(R.styleable.GuideView_clickExact,false);
        heightLightPadding = (int) attr.getDimension(R.styleable.GuideView_heightLightPadding,0);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        rect = new Rect(0,0,0,0);
        guideBeans = new ArrayList<>();

        guideTextColor = attr.getColor(R.styleable.GuideView_guideTextColor,Color.WHITE);
        guideTextSize = attr.getDimension(R.styleable.GuideView_guideTextSize, GuideViewUtils.dip2px(getContext().getApplicationContext(),16));
        textPaint = new Paint();
        textPaint.setColor(guideTextColor);
        textPaint.setTextSize(guideTextSize);
        attr.recycle();
    }


    /**
     * 设置点击事件接口
     * @param guideViewClickCallBack
     */
    public void setGuideViewClickCallBack(GuideViewClickCallBack guideViewClickCallBack){
        this.guideViewClickCallBack = guideViewClickCallBack;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right - left;
        height = bottom - top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (guideBeans == null || guideBeans.size() == 0){
            this.setVisibility(GONE);
            return;
        }
        if (guideBean == null){
            return;
        }
        //如果只是绘制某片区域，没必要判断viewbitmap是否为空。
        if (!guideBean.isSimpleRect()){
            if (guideBean.getRect() == null || guideBean.getViewBitmap() == null || guideBean.getBitmap() == null){
                this.setVisibility(GONE);
                return;
            }
        }
       if (openMore){
           canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
           for (int i=0; i<guideBeans.size(); i++){
               //优先判断是不是要绘制一篇矩阵区域
               if (guideBeans.get(i).isSimpleRect()){
                   drawSimpleShapeView(canvas,guideBeans.get(i));
                   continue;
               }
               //是否是绘制简单的集合图像，不是就绘制要说明view的图像
               if (!guideBeans.get(i).isSimpleShape()){
                   drawBuyView(canvas,guideBeans.get(i));
               }else {
                  drawSimpleShapeView(canvas,guideBeans.get(i));
               }
           }
       }else {
           //优先判断是不是要绘制一篇矩阵区域
           if (guideBean.isSimpleRect()){
               canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
               drawSimpleShapeView(canvas,guideBean);
               return;
           }
           if (!guideBean.isSimpleShape()){
               drawBuyView(canvas,guideBean);
           }else {
               canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
               drawSimpleShapeView(canvas,guideBean);
           }
       }
    }

    public int getMaskColor() {
        return maskColor;
    }

    public void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    public boolean isOpenMore() {
        return openMore;
    }

    public void setOpenMore(boolean openMore) {
        this.openMore = openMore;
    }

    public int getHeightLightPadding() {
        return heightLightPadding;
    }

    public void setHeightLightPadding(int heightLightPadding) {
        this.heightLightPadding = heightLightPadding;
    }

    public void setClickExact(boolean clickExact){
        this.clickExact = clickExact;
    }

    public int getGuideTextColor() {
        return guideTextColor;
    }

    public void setGuideTextColor(int guideTextColor) {
        this.guideTextColor = guideTextColor;
    }

    public float getGuideTextSize() {
        return guideTextSize;
    }

    public void setGuideTextSize(float guideTextSize) {
        this.guideTextSize = guideTextSize;
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
        if (!openMore){
            canvas.drawBitmap(createDstBitmap(width,height), 0, 0, paint);
        }

        if (guideBean.getRect() == null){
            return;
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
            drawShuoMingPic(canvas,guideBean,targetCenter);
        }
    }

    /**
     * 高亮区使用基本几何图形绘制
     * @param canvas
     * @param guideBean
     */
    public void drawSimpleShapeView(Canvas canvas,GuideBean guideBean){
        //int layerID = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        setPadding(guideBean);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(createSrcBitmap(width,height,guideBean.getRect()), 0, 0, paint);
        paint.setXfermode(null);
        //canvas.restoreToCount(layerID);
        //说明控件的中线坐标
        int centerLine = guideBean.getRect().left+(guideBean.getRect().right-guideBean.getRect().left)/2;
        //说明图片的左边距离
        int targetCenter = centerLine - guideBean.getBitmap().getWidth()/2;
        if (guideBean.getBitmap() != null){
            drawShuoMingPic(canvas,guideBean,targetCenter);
        }

        //如果自定义字体不为空，则绘制自定义字体
        if (guideBean.getText() != null){
            textPaint.setStrokeWidth(100);
            int left = (int) (targetCenter - (textPaint.measureText(guideBean.getText())/4));
            if (guideBean.getTextAlign() == Config.LEFT){
                canvas.drawText(guideBean.getText(),0,guideBean.getRect().bottom+guideBean.getBitmap().getHeight()+textPaint.measureText("里"),textPaint);
            }else if (guideBean.getTextAlign() == Config.RIGHT){
                canvas.drawText(guideBean.getText(),width-textPaint.measureText(guideBean.getText()),guideBean.getRect().bottom+guideBean.getBitmap().getHeight()+textPaint.measureText("里"),textPaint);
            }else {
                canvas.drawText(guideBean.getText(),left,guideBean.getRect().bottom+guideBean.getBitmap().getHeight()+textPaint.measureText("里"),textPaint);
            }
        }
    }

    /**
     * 绘制控件说明
     * @param canvas
     * @param guideBean
     */
    private void drawShuoMingPic(Canvas canvas, GuideBean guideBean,int targetCenter) {
        //根据不同的方位情况进行控件绘制
        if (guideBean.getPosition() == Config.BOTTOM){
            canvas.drawBitmap(guideBean.getBitmap(),targetCenter+guideBean.getMarginLeft(),guideBean.getRect().bottom+guideBean.getMarginTop()+guideBean.getMarginBottom(),paint);
        }else if (guideBean.getPosition() == Config.TOP){
            canvas.drawBitmap(guideBean.getBitmap(),targetCenter+guideBean.getMarginLeft(),guideBean.getRect().top+guideBean.getMarginBottom()-guideBean.getBitmap().getHeight()+guideBean.getMarginBottom(),paint);
        }else if (guideBean.getPosition() == Config.LEFT){
            canvas.drawBitmap(guideBean.getBitmap(),guideBean.getRect().left-guideBean.getBitmap().getWidth()-guideBean.getMarginLeft(),guideBean.getRect().top+guideBean.getRect().height()/2,paint);
        }else if (guideBean.getPosition() == Config.RIGHT){
            canvas.drawBitmap(guideBean.getBitmap(),guideBean.getRect().right+guideBean.getMarginRight(),guideBean.getRect().top+guideBean.getRect().height()/2,paint);
        }else if (guideBean.getPosition() == Config.LEFT_TOP){
            canvas.drawBitmap(guideBean.getBitmap(),guideBean.getRect().left-guideBean.getBitmap().getWidth()-guideBean.getMarginLeft(),guideBean.getRect().top+guideBean.getMarginBottom(),paint);
        }else if (guideBean.getPosition() == Config.LEFT_BOTTOM){
            canvas.drawBitmap(guideBean.getBitmap(),guideBean.getRect().left-guideBean.getBitmap().getWidth()-guideBean.getMarginLeft(),guideBean.getRect().bottom+guideBean.getMarginTop(),paint);
        }else if (guideBean.getPosition() == Config.RIGHT_TOP){
            canvas.drawBitmap(guideBean.getBitmap(),guideBean.getRect().right+guideBean.getMarginRight(),guideBean.getRect().top+guideBean.getMarginBottom(),paint);
        }else if (guideBean.getPosition() == Config.RIGHT_BOTTOM){
            if (guideBean.getRect().right + guideBean.getBitmap().getWidth() < width){
                canvas.drawBitmap(guideBean.getBitmap(),guideBean.getRect().right,guideBean.getRect().bottom+guideBean.getMarginTop(),paint);
            }else {
                canvas.drawBitmap(guideBean.getBitmap(),width - guideBean.getBitmap().getWidth() - Config.DEFAULT_MARGIN,guideBean.getRect().bottom+guideBean.getMarginTop(),paint);
            }
            //canvas.drawBitmap(guideBean.getBitmap(),targetCenter+guideBean.getMarginLeft(),guideBean.getRect().bottom+guideBean.getMarginTop(),paint);
        }else {
            canvas.drawBitmap(guideBean.getBitmap(),targetCenter+guideBean.getMarginLeft(),guideBean.getRect().bottom+guideBean.getMarginTop()+guideBean.getMarginBottom(),paint);
        }

    }

    /**
     * 关闭引导图
     */
    public void closeGuide(){
        this.setVisibility(GONE);
        if (guideViewClickCallBack != null){
            guideViewClickCallBack.guideEndCallback();
        }
    }

    /**
     * 销毁高亮集合，下次无法直接调用showGuide（）方法进行显示，必须重新添加高亮集合。
     */
    public void destoryGuide(){
        this.setVisibility(GONE);
        guideBeans.clear();
        guideViewClickCallBack.guideEndCallback();
    }

    /**
     * 获取高亮集合
     * @return 高亮集合
     */
    public List<GuideBean> getGuideBeans(){
        return guideBeans;
    }

    /**
     * 获取当前高亮部分，如果是一屏显示多个高亮区的，返回值为空值
     * @return 当前高亮部分
     */
    public GuideBean getCurrentGuideBean(){
        return guideBean;
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
               if (openMore){
                   closeGuide();
                   if (guideViewClickCallBack != null){
                       guideViewClickCallBack.guideMoreClick(guideBeans);
                   }
               }else {
                   if (clickExact){
                       if (guideBean.getRect().contains((int)event.getX(),(int)event.getY())){
                           //抬起手指时显示下一张引导，当索引值大于集合长度时，索引归零，并隐藏引导
                           guideIndex++;
                       }else {
                           return true;
                       }
                   }else {
                       guideIndex++;
                   }
                   if (guideIndex >= guideBeans.size()){
                       closeGuide();
                       guideIndex = 0;
                       return true;
                   }
                   guideBean = guideBeans.get(guideIndex);
                   if (guideViewClickCallBack != null){
                       guideViewClickCallBack.guideClick(guideBean,guideIndex);
                   }
                   invalidate();
               }
                break;
            default:
                break;
        }
        return true;
    }

    //设置内边距
    private void setPadding(GuideBean guideBean){
        guideBean.getRect().left = guideBean.getRect().left - heightLightPadding;
        guideBean.getRect().bottom = guideBean.getRect().bottom + heightLightPadding;
        guideBean.getRect().right = guideBean.getRect().right + heightLightPadding;
        guideBean.getRect().top = guideBean.getRect().top - heightLightPadding;
    }

    /**
     * 显示高亮区，点击后立即显示
     */
    public void showGuide(){
        this.setVisibility(VISIBLE);
        if (guideBeans != null && guideBeans.size() !=0){
            guideBean = guideBeans.get(0);
        }
        invalidate();
    }

    /**
     * 显示高亮区，点击后立即显示
     */
    public void showGuide(View view){
        //在此方法里面统一获取页面的控件基本信息。
        if (guideBeans != null && guideBeans.size() !=0){
            view.post(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<guideBeans.size();i++){
                        //获取控件位置信息
                        Rect rect = new Rect();
                        if (guideBeans.get(i).getTargetView() != null){
                            //由于未知原因，安卓4.4使用getLocationInWindow测绘时左右会出现问题，但是上下坐标没问题
                            // 而使用getGlobalVisibleRect又会出现viewpager中上下又问题，左右坐标没问题，最终为了解决此问题只能两个结合使用了
                            guideBeans.get(i).getTargetView().getGlobalVisibleRect(rect);
                            int[] local = new int[2];
                            guideBeans.get(i).getTargetView().getLocationInWindow(local);
                            rect.top = local[1];
                          /*  rect.left = local[0];
                            rect.right = local[0]+guideBeans.get(i).getTargetView().getWidth();*/
                            rect.bottom = local[1]+guideBeans.get(i).getTargetView().getHeight();
                        }else {
                            rect = guideBeans.get(i).getRect();
                        }
                        setRectInfo(rect);

                        //获取控件图片信息
                        if (guideBeans.get(i).getTargetView() != null){
                            Bitmap viewBitmap = GuideViewUtils.loadBitmapFromView(guideBeans.get(i).getTargetView());
                            if (viewBitmap == null){
                                closeGuide();
                            }
                            guideBeans.get(i).setViewBitmap(viewBitmap);
                        }
                        guideBeans.get(i).setRect(rect);
                    }
                    GuideView.this.setVisibility(VISIBLE);
                    guideBean = guideBeans.get(0);
                    invalidate();
                }
            });
        }
    }

    /**
     * 设置控件矩阵的顶部坐标和底部坐标，这两个坐标与状态栏和标题栏高度有关
     * @param rect 控件的矩阵
     */
    public void setRectInfo(Rect rect){
        Rect tempRect = new Rect(rect.left,rect.top,rect.right,rect.bottom);
        rect.top = rect.top - GuideViewUtils.getStatusBarHeight(act);
        rect.bottom = rect.bottom - GuideViewUtils.getStatusBarHeight(act);
        //如果是小于0，说明属于其他页面的viewpager
        if (tempRect.left < 0){
            //获取屏幕宽度
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            //计算viewpager页数
            int screenNum = Math.abs((int)(((Math.abs(rect.left) / (screenWidth*1f)))+0.99)) ;
            //获取view的宽度
            int width = Math.abs(tempRect.right - tempRect.left);
            rect.left = screenNum * screenWidth - Math.abs(rect.left);
            rect.right = rect.left + width;
        }

    }

    /**
     * 显示高亮区，延迟显示，如果页面过于复杂，view的绘制无法马上完成，可能需要用到此方法。
     * @param delayedTime 延时操作的时间。单位为毫秒
     */
    public void showGuide(int delayedTime){
        this.setVisibility(VISIBLE);
        if (guideBeans != null && guideBeans.size() !=0){
            guideBean = guideBeans.get(0);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        },delayedTime);
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
        dstPaint.setColor(maskColor);
        canvas.drawRect(0,0,getWidth(),getHeight(), dstPaint);
        return bitmap;
    }

    public void setGuideBeans(List<GuideBean> guideBeans){
        this.guideBeans = guideBeans;
        invalidate();
    }

    public void setActivity(Activity activity) {
        this.act = activity;
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
        scrPaint.setColor(Color.parseColor("#ec6941"));
        //更具不同的传入形状绘制对应形状的高亮区
        if (Config.RECT == guideBean.getShape()){
            canvas.drawRect(rect, scrPaint);
        }else if (Config.CIRCLE == guideBean.getShape()){
            canvas.drawCircle(guideBean.getRect().left+guideBean.getRect().width()/2,guideBean.getRect().top+guideBean.getRect().width()/2,Math.max(guideBean.getRect().width(),guideBean.getRect().width())/2, scrPaint);
        }else if (Config.ROUNDED_RECT == guideBean.getShape()){
            RectF rectF = new RectF(rect.left,rect.top,rect.right,rect.bottom);
            canvas.drawRoundRect(rectF, Config.ROUNDED_RECT_VALUE, Config.ROUNDED_RECT_VALUE,scrPaint);
        }else if (Config.OVAL == guideBean.getShape()){
            RectF rectF = new RectF(rect.left,rect.top,rect.right,rect.bottom);
            canvas.drawOval(rectF,scrPaint);
        }else {
            canvas.drawRect(rect, scrPaint);
        }
        return bitmap;
    }

    /**
     * view的配置文件，务必在调用showGuide方法前配置完guideview
     */
    public static class Config{
        /**
         * 说明图片在控件的左边中间位置
         */
        public static final int LEFT = 1;

        /**
         * 说明图片在控件的左上角位置
         */
        public static final int LEFT_TOP = 12;

        /**
         * 说明图片在控件的左下角位置
         */
        public static final int LEFT_BOTTOM = 13;

        /**
         * 说明图片在控件的底部中间位置
         */
        public static final int BOTTOM = 3;

        /**
         * 说明图片在控件的顶部中间位置
         */
        public static final int TOP = 2;

        /**
         * 说明图片在控件的右边中间位置
         */
        public static final int RIGHT = 4;

        /**
         * 说明图片在控件的右上角位置
         */
        public static final int RIGHT_TOP = 42;

        /**
         * 说明图片在控件的右下角位置
         */
        public static final int RIGHT_BOTTOM = 43;

        /**
         * 如果有需要，可以手动设置actionbar的高度,默认为0
         * 配置这个参数时必须在设置引导集合前设置，否则无效
         */
        public static int ACTIONBAR_HEIGHT = -1;

        /**
         * 矩形
         */
        public static final byte RECT = 0;

        /**
         * 圆形
         */
        public static final byte CIRCLE = 1;

        /**
         * 椭圆形
         */
        public static final byte OVAL = 2;

        /**
         * 圆角矩形
         */
        public static final byte ROUNDED_RECT = 3;

        /**
         * 圆角矩形的圆角值,只对绘制圆角矩形的情况生效
         */
        public static float ROUNDED_RECT_VALUE = 20;

        /**
         * 默认margin
         */
        public static float DEFAULT_MARGIN = 20;

    }
}
