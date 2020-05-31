package com.changgou.framework.entity;

/**
 * 返回码
 */
public class StatusCode {

    public static final int OK = 20000;//成功
    public static final int NOT_FIND = 20001;//未能查询到数据
    public static final int ERROR = 20002;//失败
    public static final int LOGINERROR = 20003;//用户名或密码错误
    public static final int ACCESSERROR = 20004;//权限不足
    public static final int REMOTEERROR = 20005;//远程调用失败
    public static final int REPERROR = 20006;//重复操作

}
