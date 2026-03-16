package cn.alex.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by WCY on 2021/8/3
 */
@WebFilter(urlPatterns = "/*", filterName = "reqResFilter")
public class ReqResFilter implements Filter {
    // web容器加载时
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("reqResFilter init");
    }

    // 请求
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("reqResFilter doFilter");
        // 请求放行
        chain.doFilter(request, response);
    }

    // web容器销毁时
    @Override
    public void destroy() {
        System.out.println("reqResFilter destroy");
    }
}
