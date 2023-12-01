package org.example.service;

import org.example.utils.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonAbstract implements CommonService {

    protected final String success = "success:";

    protected final String error = "error:";

    protected Integer pageCount = null;

    protected int currentPage = 1;
    protected int initLimit = 10;

    protected int limit = 10;
    protected int lastPage;
    protected String like;
    protected boolean loop = true;
    protected Object params;

    public interface CallbackInterface {
        void getCallbackParameters(Map<String, String> paramMap);
    }

    public void setLikeOtherParams(Object params) {
        this.params = params;
    }

    public void callbackParams(CallbackInterface ci) throws IllegalAccessException {
        if (params == null) throw new ServiceException(ServiceException.ILLEGAL_REQUEST_PARAMETER);
        HashMap<String, String> paramMap = new HashMap<>();
        Field[] declaredFields = params.getClass().getDeclaredFields();
        for (Field field: declaredFields) {
            field.setAccessible(true);
            Object o = field.get(params);
            if (o == null) continue;
            if (!(o instanceof String)) continue;
            paramMap.put(field.getName(), (String) o);
        }
        ci.getCallbackParameters(paramMap);
    }

    @Override
    public void getMaxPage() {
        int maxPage = pageCount / limit;
        if (maxPage == 0) {
            lastPage = 1;
            return;
        }
        lastPage = (pageCount & limit) != 0 ? maxPage + 1 : maxPage;
    }

    @Override
    public void examinePage(String pageStr, String limitStr) {

        try {
            if (pageStr != null)
                currentPage = Integer.parseInt(pageStr);
            if (limitStr != null)
                limit = Integer.parseInt(limitStr);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        }

        if (limit < 10 || limit > 20) limit = initLimit;

        if (currentPage > lastPage)
            currentPage = lastPage;
        else if (currentPage < 1)
            currentPage = 1;
    }

    @Override
    public void setPageLimit(String like, String currentPage, String limit) {
        this.like = StringUtil.isNotEmpty(like) ? like : null;
        getTotalCount();
        getMaxPage();
        examinePage(currentPage, limit);
    }

    @Override
    public int getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public int getLimit() {
        return this.limit;
    }

    @Override
    public int getLastPage() {
        return this.lastPage;
    }
}
