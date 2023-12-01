package org.example.mvc;

public class DispatcherServletException extends RuntimeException {
    public DispatcherServletException(String msg){
        super(msg);
    }
    public DispatcherServletException(String msg, Exception e){
        super(msg, e);
    }

    protected static final DispatcherServletException NOT_FOUND_PATH = new DispatcherServletException("DispatcherServletException - 找不到路径");
    protected static DispatcherServletException NOT_FOUND_PATH(String msg) {
        NOT_FOUND_PATH.addSuppressed(new DispatcherServletException(msg));
        return NOT_FOUND_PATH;
    }
}
