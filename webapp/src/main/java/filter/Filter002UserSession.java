package filter;

import databaseentity.SysUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "Filter002UserSession",urlPatterns = "/*")
public class Filter002UserSession implements Filter {

    private List<String> noCheckPage= Arrays.asList("/views/login.jsp", "/LoginAction", "/GetVerfiyCodeServlet","/");


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        String projectName=request.getContextPath();
        String path=request.getRequestURI();
        path=path.replace("/webapp","");

        if(noCheckPage.contains(path)||path.endsWith(projectName)||path.endsWith(".css") || path.endsWith(".js") ){
            chain.doFilter(req, resp);
            return;
        }


        SysUser userModel = (SysUser) request.getSession().getAttribute("UserInfo");
        if (userModel == null) {
            //request.setAttribute("msg","用户还未登录，无法进行访问");
            //request.getRequestDispatcher(request.getContextPath()+"/views/login.jsp").forward(req,resp);
            //return;
            PrintWriter out=resp.getWriter();
            out.print("<script language=javascript>alert('您还没有登录！！！');window.location.href='"+request.getContextPath()+"/views/login.jsp';</script>");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
