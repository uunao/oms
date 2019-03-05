/**
 * Program  : Message.java
 * Author   : songkun
 * Create   : 2014年6月28日 下午12:53:10
 */

package com.nh.oms.transform;

/**
 * 文本消息输出类
 *
 * @author wcc
 * @version 1.0.0
 */
public class MessageOut<T> {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 输出信息
     */
    private String message;

    /*
    输出码
     */
    private String code;

    public MessageOut() {
    }

    public MessageOut(boolean success, String code, String message) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 修改成功/失败 提示信息
    public static final MessageOut<Object> UPDATE_OK_MESSAGE = new MessageOut<Object>(true, "000000", "操作成功");

    public static final MessageOut<Object> UPDATE_FAIL_MESSAGE = new MessageOut<Object>(false, "000001", "必填项缺失，请检查");

    public static final MessageOut<Object> Login_K3_FAIL_MESSAGE = new MessageOut<Object>(false, "000002", "K3接口登陆异常，请检查");

    public static final MessageOut<Object> Get_FAIL_MESSAGE = new MessageOut<Object>(false, "000003", "获取数据异常，请检查");

    public static final MessageOut<Object> Repeat_Fail_MESSAGE = new MessageOut<Object>(false, "000004", "重复推送");



}
