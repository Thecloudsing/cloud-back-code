package org.example.utils;

import org.example.service.CommonService;

import javax.servlet.http.HttpServletRequest;

/**
 * @description：返回统一结果集
 */
public class Result<T> {

    private String message;
    private int retCode;
    private T data;
    private int currentPage;
    private int lastPage;
    private int totalCount;

    private Result(T data) {
        this.retCode = 200;
        this.message = "成功";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if(cm == null){
            return;
        }
        this.retCode = cm.getRetCode();
        this.message = cm.getMessage();
    }

    public Result(CodeMsg cm, String message) {
        if(cm == null){
            return;
        }
        this.retCode = cm.getRetCode();
        this.message = cm.getMessage() + "---" + message;
    }


    /**
     * 成功时候的调用
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 成功，不需要传入参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success(){
        return (Result<T>) success("");
    }
    /**
     * 失败时候的调用
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    public static Result<String> Redirect(int retCode, String redirectUrl, String redirectMessage){
        Result<String> tResult = new Result<>(redirectUrl);
        tResult.retCode = retCode;
        tResult.message = redirectMessage;
        return tResult;
    }

    public static Result<String> auto(String message) {
        if (StringUtil.isEmpty(message))
            return error(CodeMsg.PARAMETER_ISNULL, message);
        if (message.split(":")[0].equals("success"))
            return success(message);
        return error(CodeMsg.PARAMETER_ISNULL, message);
    }

    public static <T> Result<T> auto(T o) {
        if (o != null)
            return success(o);
        return error(CodeMsg.PARAMETER_ISNULL);
    }
    /**
     * 失败时候的调用,扩展消息参数
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm,String msg){
        return new Result<T>(cm, msg);
    }

    public interface InsertResult <T> {
        Result<T> insert();
    }
    public static <T> Result<T> wrapResult(HttpServletRequest request, boolean currentLast, CommonService commonService, InsertResult<T> insertResult) {
        String like = request.getParameter("like");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        commonService.setPageLimit(like, page, limit);
        Result<T> result = insertResult.insert();
        result.setTotalCount(commonService.getTotalCount());
        result.setCurrentPage(currentLast ? commonService.getLastPage() : commonService.getCurrentPage());
        result.setLastPage(commonService.getLastPage());
        return result;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }
    public int getRetCode() {
        return retCode;
    }
}
