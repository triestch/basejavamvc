package action;

import databaseentity.SysUser;
import impl.SysUserService;
import iservice.ISysUserService;

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

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //region 定义变量

        ISysUserService sysService = new SysUserService();
        String path=request.getContextPath();

        //endregion

        //region 获取参数
        String n0=request.getParameter("n0");
        String n1=request.getParameter("n1");
        String n2=request.getParameter("n2");
        String[] n3=request.getParameterValues("n3");
        String n4=request.getParameter("n4");
        String n5=request.getParameter("n5");

        //endregion

        //region 信息初始化

        SysUser userModel =new SysUser();

        userModel.setPkid(UUID.randomUUID().toString());
        userModel.setUsertype(1);
        userModel.setOrdshow(100);
        userModel.setUsestate(1);
        userModel.setIsdel(0);
        userModel.setCreatetime(new Date());
        userModel.setCreaterid("556");
        userModel.setCreatername("ganmm");

        userModel.setLoginname(n0);

        userModel.setTruename(n1);
        userModel.setSex(Integer.parseInt(n2));

        String n3str=Arrays.toString(n3).trim().replace(" ","");
        userModel.setDeptname(n3str.substring(1,n3str.length()-1));
        userModel.setDutyname(n4);


        Date d1= null;
        Date d2=null;
        try {
            //d1报错
            //d1 = new SimpleDateFormat("yyyy-MM-dd Hh:mm:ss").parse(n5);
            d2=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(n5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userModel.setUpdatetime(d2);

        //endregion

        sysService.insertEntity(userModel);

        response.sendRedirect(path+ "/UserListServlet?method=findall");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
