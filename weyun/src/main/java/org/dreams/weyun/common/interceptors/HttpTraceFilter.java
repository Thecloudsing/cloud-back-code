package org.dreams.weyun.common.interceptors;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
@WebFilter(urlPatterns = "/*")
public class HttpTraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
