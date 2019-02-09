package action;

import image.VerfiyCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet("/GetVerfiyCodeServlet")
public class VerfiyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Map<String, Object> picMap= VerfiyCode.generateCodeAndPic();
            request.getSession().setAttribute("session_vcode",picMap.get("code").toString());

            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", -1);

            response.setContentType("image/jpeg");

            // 将图像输出到Servlet输出流中。
            ServletOutputStream sos;
            try {
                sos = response.getOutputStream();
                ImageIO.write((RenderedImage)picMap.get("codePic"), "jpeg", sos);
                sos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("生成验证码失败",e);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
