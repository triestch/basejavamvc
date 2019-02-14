package filter;

import comuse.EncodingRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "Filter001Encoding",urlPatterns = "/*")
public class Filter001Encoding implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        HttpServletRequest request=(HttpServletRequest)req;
        EncodingRequest er=new EncodingRequest(request);
        //post
        er.setCharacterEncoding("UTF-8");

        chain.doFilter(er, resp);

        /*HttpServletRequest request=(HttpServletRequest)req;

        if(request.getMethod().equals("GET"))
        {
            EncodingRequest er=new EncodingRequest(request);
            chain.doFilter(er, resp);
        }
        else if(request.getMethod().equals("POST"))
        {
            chain.doFilter(req, resp);
        }*/

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
