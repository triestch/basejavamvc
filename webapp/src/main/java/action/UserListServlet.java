package action;

import comuse.PageBean;
import databaseentity.SysUser;
import impl.PageService;
import iservice.IPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mename=request.getParameter("method");
        String reurl="";
        switch (mename)
        {
            case "findall":
                reurl=queryInfo(request,response);
                break;
        }

        request.getRequestDispatcher(reurl+"?method=findall").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public String queryInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PageBean<SysUser> pageBean=new PageBean<>();
        pageBean.setTbName("sys_user");
        pageBean.setTbId("pkid");
        pageBean.setTbColumn("pkid,loginname,truename,createtime");
        pageBean.setTbWhere(" where deptname='项目部' ");
        pageBean.setTbOrder(" order by truename asc ");

        String pc=request.getParameter("pc");
        pageBean.setPc(Integer.parseInt(pc));
        pageBean.setPs(3);

        IPageService<SysUser> pageService=new PageService<SysUser>();
        pageService.queryPage(pageBean,SysUser.class);


        request.setAttribute("pageBean",pageBean);
        return "/views/user_list.jsp";
    }
}
