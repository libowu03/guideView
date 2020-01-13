package com.kit.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制弹窗顺序的管理类
 * @author bohu
 * @date 2019/10/24
 */
public class WindowDialogManager implements DialogInterface.OnDismissListener, Application.ActivityLifecycleCallbacks {
    /**
     * 弹窗集合
     */
    private List<Object> dialog;
    private FragmentManager transaction;
    private String tag;
    /**
     * 两个弹窗之间的时间间隔，默认-1，-1表示不存在时间间隔，一个弹窗完后立马显示下一个
     */
    private int second = -1;
    /**
     * 用于设置弹窗延迟显示
     */
    private Handler handler;
    /**
     * 用于监听活动的什么周期，单activity处于销毁或不可见时显示弹窗会出现崩溃，所以需要在活动的ondestory，onpause，onstopped中清除点延迟显示的弹窗
     * 为了节省手机性能，在ondestory，onpause，onstopped或显示完所有弹窗后需要取消生命周期的监听
     */
    private Application application;
    private Handler firstDaly;
    private long dalyTime = -1;

    public WindowDialogManager(FragmentManager fragmentManager, String tag, Application application) {
        this.transaction = fragmentManager;
        this.tag = tag;
        this.application = application;
        //在activity的onpause和ondestory触发后才弹出窗口会导致没有附着对象导致软件闪退，所以需要经停一下软件的什么周期
        application.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 正式开始显示弹窗内容
     */
    private void showDialog(){
        try{
            if (dialog == null || dialog.size() == 0 || transaction == null){
                return;
            }
            if (dialog.get(0) instanceof Dialog){
                ((Dialog)dialog.get(0)).setOnDismissListener(this);
                ((Dialog)dialog.get(0)).show();
            }else if (dialog.get(0) instanceof DialogFragment){
                ((DialogFragment)dialog.get(0)).show(transaction,tag);
                //没法直接设置监听，应为设置监听必须等到onCreateDialog后才可以正常进行，所以只能延迟设置监听。
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ((DialogFragment)dialog.get(0)).getDialog().setOnDismissListener(WindowDialogManager.this);
                        }catch (Exception e){
                            Log.e("errorLog","reason:"+e.getLocalizedMessage());
                        }
                    }
                },250);
            }
        }catch (Exception e){
            if (e.getLocalizedMessage() != null){
                L.e("kitErrorMsg","=============="+e.getLocalizedMessage());
            }
        }
    }

    /**
     * 供给外部调用的显示弹窗方法
     */
    public void show(){
        //如果存在首次延迟显示，则延迟显示
        if (dalyTime != -1){
            firstDaly.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            },dalyTime * 1000);
        }else {
            showDialog();
        }
    }

    /**
     * 首次弹窗延迟显示设置，单位秒
     * @param second 延迟时间
     */
    public void setFirstDalyTime(long second){
        if (firstDaly != null){
            firstDaly.removeMessages(0);
        }
        firstDaly = new Handler();
        this.dalyTime = second;
    }

    /**
     * 设置弹窗集合
     * @param dialog 弹窗集合
     */
    public void setDialogList(List<Object> dialog){
        this.dialog = dialog;
    }

    /**
     * 添加单个弹窗到集合中去
     * @param obj
     */
    public void addDialogItem(Object obj){
        if (dialog == null){
            dialog = new ArrayList<>();
        }
        dialog.add(obj);
    }

    /**
     *    ===============================Dialog的关闭事件==============================================
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        showNextDialog();
    }

    private void showNextDialog() {
        if (this.dialog.size() == 0){
            if (application != null){
                application.unregisterActivityLifecycleCallbacks(WindowDialogManager.this);
            }
            return;
        }
        this.dialog.remove(0);
        if (second != -1){
            handler.removeMessages(0);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            },second * 1000);
        }else {
            showDialog();
        }
    }
    //===============================Dialog的关闭事件End==============================================


    /**
     * 清除所有弹窗
     */
    public boolean removeAllDialog(){
        if (dialog != null){
            dialog.clear();
            return true;
        }else {
            return false;
        }
    }

    /**
     * 设置弹窗时间间隔
     * @param second 时间间隔，单位为妙
     */
    public void setShowOffsetTime(int second){
        this.second = second;
        handler = new Handler();
    }

    /**
     * 通过弹窗索引清除弹窗
     * @param index
     * @return 清除结果，返回false则弹窗未被清除
     */
    public boolean removeDialog(int index){
        if (dialog != null && dialog.size()>0 && index <= dialog.size()-1 && index > 0){
            dialog.remove(index);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 通过对象清除弹窗
     * @param obj
     * @return 清除结果，返回false则弹窗未被清除
     */
    public boolean removeDialog(Object obj){
        if (dialog != null && dialog.size()>0 && dialog.contains(obj)){
            dialog.remove(obj);
            return true;
        }else {
            return false;
        }
    }

    //============================生命周期的监听，防止弹窗附着activity失败===================================================
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (handler != null){
            application.unregisterActivityLifecycleCallbacks(this);
            handler.removeMessages(0);
            if (firstDaly != null){
                firstDaly.removeMessages(0);
            }
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (handler != null){
            application.unregisterActivityLifecycleCallbacks(this);
            handler.removeMessages(0);
            if (firstDaly != null){
                firstDaly.removeMessages(0);
            }
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }
    //============================生命周期的监听，防止弹窗附着activity失败End===================================================

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (handler != null){
            application.unregisterActivityLifecycleCallbacks(this);
            handler.removeMessages(0);
            if (firstDaly != null){
                firstDaly.removeMessages(0);
            }
        }
    }
    //============================生命周期的监听，防止弹窗附着activity失败End===================================================

    public static class Build{
        private List<Object> dialog;
        private WindowDialogManager windowDialogManager;
        private FragmentManager fragmentManager;
        private String tag;
        private Application application;
        private Handler handler;
        private long dialogDelayTime = -1;

        public Build(FragmentManager fragmentManager, String tag, Application application){
            this.fragmentManager = fragmentManager;
            this.tag = tag;
            handler = new Handler();
        }

        /**
         * 添加弹窗
         * @param dialogFragment
         * @return
         */
        public Build addDialog(DialogFragment dialogFragment){
            if (dialog == null){
                dialog = new ArrayList<>();
            }
            dialog.add(dialogFragment);
            return this;
        }

        public void setStartDialogDelayTime(long second){
            this.dialogDelayTime = second;
        }

        /**
         * 添加弹窗
         * @param dialog
         * @return
         */
        public  Build addDialog(Dialog dialog){
            if (this.dialog == null){
                this.dialog = new ArrayList<>();
            }
            return this;
        }

        /**
         * 显示弹窗
         * @return
         */
        public WindowDialogManager show(){
            if (windowDialogManager == null){
                windowDialogManager = new WindowDialogManager(fragmentManager,tag,application);
            }
            windowDialogManager.dialog = dialog;
            if (dialogDelayTime == -1){
                windowDialogManager.showDialog();
            }else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        windowDialogManager.showDialog();
                    }
                },dialogDelayTime * 1000);
            }
            windowDialogManager.firstDaly = handler;
            dialog.clear();
            return windowDialogManager;
        }
    }
}
