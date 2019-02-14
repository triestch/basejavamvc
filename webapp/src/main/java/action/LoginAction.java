package action;

import databaseentity.SysUser;
import impl.SysUserService;
import iservice.ISysUserService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/LoginAction")
public class LoginAction extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String name=request.getParameter("txt_UserName");
        String pwd=request.getParameter("txt_UserPwd");
        String checkNum=request.getParameter("txt_CheckCode").toUpperCase();
        String path=request.getContextPath();

        ISysUserService userService=new SysUserService();
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("session_vcode").toString().toUpperCase();

        try {
            if (checkNum != null && !"".equals(checkNum) && sessionCode != null && !"".equals(sessionCode)) {
                if (!checkNum.equals(sessionCode)) {
                    request.setAttribute("msg", "验证码录入错误");
                    request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                } else {
                    SysUser userModel = userService.loginDeal(name, pwd);
                    if (userModel == null) {
                        request.setAttribute("msg", "用户不存在或密码录入错误");
                        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                        return;
                    } else {
                        if (userModel.getUsestate() == 0) {
                            request.setAttribute("msg", "用户被停用");
                            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                            return;
                        } else {
                            request.getSession().setAttribute("UserInfo",userModel);
                            response.sendRedirect(path+ "/UserListServlet?method=findall");
                            //return;
                        }
                    }
                }
            }
            else {
                request.setAttribute("msg", "验证码失败");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                //return;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
