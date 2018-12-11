package com.example.demo.dto;

/**
 * Created by liulq on 2018-07-31 .
 */
public enum ServiceMessageSet {
    OK(20100, "服务正常"),
    ERROR(50100,"调用服务异常,请联系管理员!"),

    // 监控采集错误码
    MONITOR_REQ_TYPE_ERROR(40400, "请求类型错误"),
    MONITOR_INTERNAL_SERVER_CONFIG_ERROR(40401, "内部服务错误配置异常"),
    MONITOR_INTERNAL_SERVER_NODATA_ERROR(40402, "无结果数据"),

    //镜像仓库错误号码
    UNSATISFIED_WITH_CONSTRAINTS_OF_THE_PROJECT(50400,"创建项目失败"),
    USER_NEED_TO_LOGIN_FIRST(50401,"用户必须先登录"),
    PROJECT_NAME_ALREADY_EXISTS(50402,"项目名称已经存在"),
    REQUEST_IS_NOT_SUPPORTED(50403,"不支持请求的媒体类型，它必须是“application/json"),
    INVALID_PROJECT_ID(50404,"无效的项目ID"),
    PROJECT_DOES_NOT_EXIST(50405,"项目不存在"),
    CANT_DELETE_PROJECT_CONTAINS_POLICIES(50406,"项目包含策略，不能被删除"),
    PROJECT_IS_NOT_PUBLIC_OR_USER_IS_IRRELEVANT(50407,"项目不是公共的，或者当前用户与项目无关"),
    INVALID_IMAGE_NAME(50408,"无效的镜像名"),
    DELETE_IMAGE_FORBIDDEN(50409,"删除镜像被禁止"),
    DELETE_IMAGE_REPOSITORY_NOT_FOUND(50410,"仓库找不到"),
    REPOSITORY_OR_TAG_NOT_FOUND(50411,"镜像名或tag找不到"),
    
    UNEXPECTED_INTERNAL_ERRORS(50500,"未知的内部错误"),
    DONT_KNOW_WHAT_IS_THE_ERROR(50501,"不能捕获的错误"),
    
    NAMESPACES_CREATE_FAIL(20301,"kubernetes命名空间创建失败"),
    NAMESPACES_DELETE_FAIL(20302,"kubernetes命名空间删除失败"),
    FILE_NOT_EXIST(20101,"文件不存在"),
    APP_NAME_REPLICATION(20201, "应用已存在");
	
    private int code;
    private String msg;

    ServiceMessageSet(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
