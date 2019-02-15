package action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import databaseentity.SysUser;
import impl.SysUserService;
import iservice.ISysUserService;
import jsonmodel.ReturnStr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/DelUserServlet")
public class DelUserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String reStr = "";
        switch (action) {
            case "DelInfo":
                reStr = DelInfo(request, response);
                break;
        }

        response.getWriter().write(reStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String DelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString;
        ReturnStr reStr = new ReturnStr();
        ObjectMapper mapper = new ObjectMapper();
        String pkid = request.getParameter("pkid");
        ISysUserService sysService = new SysUserService();

        try {
            SysUser userModel = sysService.queryEntity(" pkid=? ", pkid);

            userModel.setIsdel(1);
            userModel.setDeltime(new Date());

            sysService.updateEntity(userModel, userModel.getPkid());

            reStr.setSuccess(true);

        } catch (Exception e) {
            e.printStackTrace();
            reStr.setMsg("发生异常删除失败");
        }

        jsonString = mapper.writeValueAsString(reStr);

        return jsonString;
    }
}
