package com.libowu.guide.exception;

/**
 * @author libowu
 * @date 2019/07/30
 * 自定义错误
 */
public class GuideViewException extends Exception {

    public GuideViewException(){
        super();
    }

    public GuideViewException(String msg){
        super(msg);
    }

    public GuideViewException(String msg, Throwable throwable){
        super(msg,throwable);
    }

    public GuideViewException(Throwable throwable){
        super(throwable);
    }
}
