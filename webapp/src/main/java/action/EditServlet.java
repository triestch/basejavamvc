package action;

import comuse.PageBean;
import databaseentity.SysUser;
import impl.PageService;
import impl.SysUserService;
import iservice.IPageService;
import iservice.ISysUserService;
import lang.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //region 定义变量

        ISysUserService sysService = new SysUserService();
        String path=request.getContextPath();

        //endregion

        //region 获取参数
        String pkid=request.getParameter("pkid");

        String n0=request.getParameter("n0");
        String n1=request.getParameter("n1");
        String n2=request.getParameter("n2");
        String[] n3=request.getParameterValues("n3");
        String n4=request.getParameter("n4");
        String n5=request.getParameter("n5");

        //endregion

        //region 信息初始化

        SysUser userModel =sysService.queryEntity(" pkid=? ",pkid);


        userModel.setLoginname(n0);

        userModel.setTruename(n1);
        userModel.setSex(Integer.parseInt(n2));

        String n3str=Arrays.toString(n3).trim().replace(" ","");
        userModel.setDeptname(n3str.substring(1,n3str.length()-1));
        userModel.setDutyname(n4);


        Date d2=null;
        try {
            d2=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(n5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userModel.setUpdatetime(d2);

        //endregion

        sysService.updateEntity(userModel,pkid);

        response.sendRedirect(path+ "/UserListServlet?method=findall");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mename=request.getParameter("method");
        String reurl="";
        switch (mename)
        {
            case "edit":
                reurl="/views/edit.jsp";
                break;
            case "view":
                reurl="/views/view.jsp";
                break;
        }
        getInfo(request, response);

        request.getRequestDispatcher(reurl).forward(request, response);

    }

    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ISysUserService sysService = new SysUserService();
        String pkid=request.getParameter("id");

        SysUser userModel =sysService.queryEntity(" pkid=? ",pkid);
        request.setAttribute("userModel",userModel);
    }
}
