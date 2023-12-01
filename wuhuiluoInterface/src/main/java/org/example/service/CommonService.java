package org.example.service;

public interface CommonService {
    void setLikeOtherParams(Object params);
    void setPageLimit(String like, String currentPage, String limit);
    int getTotalCount();
    void getMaxPage();
    void examinePage(String page, String limit);
    int getCurrentPage();
    int getLimit();
    int getLastPage();
}
