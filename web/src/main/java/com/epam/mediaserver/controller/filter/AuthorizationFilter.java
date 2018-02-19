package com.epam.mediaserver.controller.filter;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Path;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute(Attribute.ATTRIBUTE_USER_LOGIN) == null & (!req.getRequestURI()
            .endsWith("home"))) {
            System.out.println(req.getRequestURI().endsWith("home"));
            System.out.println(req.getRequestURI());
            resp.sendRedirect(Path.PATH_TO_INDEX_PAGE);

            //req.getRequestDispatcher(Path.PATH_TO_INDEX_PAGE).forward(req,resp);
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
