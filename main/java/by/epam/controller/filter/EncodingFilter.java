package by.epam.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet Filter implementation class EncodingFilter
 */

public class EncodingFilter  implements Filter{


    private String encoding = "utf-8";

    /**
     * Method is used to set encoding for the request and response objects
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);

    }

    /**
     * @see Filter#init(FilterConfig)
     */

    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }


    /**
     * @see Filter#destroy()
     */

    public void destroy() {
        encoding = null;
    }
}
