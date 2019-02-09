package comuse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

public class EncodingRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public  EncodingRequest(HttpServletRequest request)
    {
        super(request);
        this.request=request;

    }


    public String getParameter(String s) {
        String value=request.getParameter(s);
        try {
            value=new String(value.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("过滤器转换字符集失败",e);
        }

        return value;
    }

}
