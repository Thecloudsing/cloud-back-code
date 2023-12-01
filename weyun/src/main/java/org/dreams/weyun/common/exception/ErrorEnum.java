package org.dreams.weyun.common.exception;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
public interface ErrorEnum {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    Integer getErrorCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getErrorMsg();
}
