package action;

import comuse.PageBean;
import databaseentity.SysUser;
import impl.PageService;
import iservice.IPageService;
import lang.StringUtil;

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

        request.getRequestDispatcher(reurl).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a1="";
        String a2="";
        try {
            a1=request.getParameter("sname").trim();
            a2=java.net.URLDecoder.decode(a1, "UTF-8");
        }
       catch (Exception ex)
       {
           ex.printStackTrace();
       }
        doPost(request,response);
    }

    public String queryInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        StringBuffer urlStr=new StringBuffer();
        urlStr.append("/UserListServlet?method=findall");

        StringBuffer whereStr=new StringBuffer();
        whereStr.append(" where 1=1 ");

        String slogname=request.getParameter("slogname").trim();
        String sname=request.getParameter("sname").trim();
        if(!StringUtil.strIsNullOrEmpty(slogname))
        {
            request.setAttribute("slogname",slogname);
            urlStr.append("&slogname=").append(slogname);
            whereStr.append(" and loginname like '%").append(slogname).append("%' ");
        }
        if(!StringUtil.strIsNullOrEmpty(sname))
        {
            request.setAttribute("sname",sname);
            String zmStr=java.net.URLEncoder.encode(sname, "UTF-8");
            urlStr.append("&sname=").append(zmStr);
            whereStr.append(" and truename like '%").append(sname).append("%' ");
        }

        PageBean<SysUser> pageBean=new PageBean<>();
        pageBean.setTbName("sys_user");
        pageBean.setTbId("pkid");
        pageBean.setTbColumn("pkid,loginname,truename,createtime");
        pageBean.setTbWhere(whereStr.toString());
        pageBean.setTbOrder(" order by truename asc ");
        pageBean.setListurl(urlStr.toString());


        String  pc=request.getParameter("pc").trim();
        if(!StringUtil.strIsNullOrEmpty(pc))
        {
            //urlStr.append("&pc=").append(pc);
            pageBean.setPc(Integer.parseInt(pc));
        }
        //pageBean.setPs(3);

        IPageService<SysUser> pageService=new PageService<SysUser>();
        pageService.queryPage(pageBean,SysUser.class);


        request.setAttribute("pageBean",pageBean);
        return "/views/user_list.jsp";
    }
}
