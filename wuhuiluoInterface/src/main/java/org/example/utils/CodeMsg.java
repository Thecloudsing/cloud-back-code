package org.example.utils;

/**
 * @description：返回提示
 */
public class CodeMsg {
    private int retCode;
    private String message;
    // 按照模块定义CodeMsg
    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(200,"success");
    public static CodeMsg REDIRECT = new CodeMsg(300,"Redirect");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500,"服务端异常");
    public static CodeMsg UNABLE_TO_PROCESS = new CodeMsg(501,"无法处理参数");
    public static CodeMsg PATH_NOT_EXSIST = new CodeMsg(404,"路径不存在");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(5001,"输入参数为空");
    // 业务异常
    public static CodeMsg USER_NOT_EXSIST = new CodeMsg(5002,"用户不存在");
    public static CodeMsg ONLINE_USER_OVER = new CodeMsg(5003,"在线用户数超出允许登录的最大用户限制。");
    public static CodeMsg SESSION_NOT_EXSIST =  new CodeMsg(5004,"不存在离线session数据");
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(5005,"查找不到对应数据");
    public static CodeMsg USER_OR_PASS_ERROR = new CodeMsg(5006,"账号或者密码错误，请重试！");
    public static CodeMsg USER_ROUTE_ERROR = new CodeMsg(5007,"没有权限");


    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}