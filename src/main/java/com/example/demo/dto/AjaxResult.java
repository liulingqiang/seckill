package com.example.demo.dto;

/**
 * Created by liulq on 2017-11-15 .
 */
public class AjaxResult {
    private int code = ServiceMessageSet.OK.getCode();

    private String msg = ServiceMessageSet.OK.getMsg();

    private Object result;
    public AjaxResult(){}

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(Object result) {
        this(ServiceMessageSet.OK.getCode(), "OK");
        this.result = result;
    }

    public static AjaxResult error() {
        return error(ServiceMessageSet.ERROR.getCode(), ServiceMessageSet.ERROR.getMsg());
    }

    public static AjaxResult error(String msg) {
        return error(ServiceMessageSet.ERROR.getCode(), msg);
    }

    public static AjaxResult error(int code, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        return ajaxResult;
    }

    public static AjaxResult ok(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setResult(data);
        return ajaxResult;
    }

    public static AjaxResult ok() {
        return new AjaxResult();
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "AjaxResult [code=" + code + ", msg=" + msg + ", result=" + result + "]";
    }
}
