package com.epam.mediaserver.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharachterEncodingFilter implements Filter {

    private String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        req.setCharacterEncoding(encoding);
        res.setCharacterEncoding(encoding);
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {
    }
}
