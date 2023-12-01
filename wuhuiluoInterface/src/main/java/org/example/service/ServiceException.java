package org.example.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    /**
    *  illegal_request_parameter
    */
    public static final String ILLEGAL_REQUEST_PARAMETER = "非法请求参数";

    /**
    *  request_parameter_null_value
    */
    public static final String REQUEST_PARAMETER_NULL_VALUE = "请求参数为空";

    /**
     *  the_new_password_cannot_be_the_same_as_the_old_password
     */
    public static final String THE_NEW_PASSWORD_CANNOT_BE_THE_SAME_AS_THE_OLD_PASSWORD = "新密码不能和旧密码相同";

    /**
     *  this_account_has_not_been_registered
     */

    public static final String THIS_ACCOUNT_HAS_NOT_BEEN_REGISTERED = "该账号未被注册";

    /**
     *
     * original_password_error
     */

    public static final String ORIGINAL_PASSWORD_ERROR = "原密码错误";

    /**
     *  the_username_has_already_been_registered
     */

    public static final String THE_USERNAME_HAS_ALREADY_BEEN_REGISTERED = "该用户名已经被注册";

    /**
     *  the_account_has_already_been_registered
     */

    public static final String THE_ACCOUNT_HAS_ALREADY_BEEN_REGISTERED = "该账号已经被注册";
}
