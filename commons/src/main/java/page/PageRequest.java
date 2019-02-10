package page;

import comuse.PageBean;

import javax.servlet.http.HttpServletRequest;

public class PageRequest<T> {
    public PageBean<T> GetPageBean(HttpServletRequest request) {
        PageBean<T> pageBean=new PageBean<>();

        return pageBean;
    }

    private void SetPageSize(PageBean<T> pageBean)
    {
        if (pageBean.getPs()==0) {
            pageBean.setPs(10);
        }
    }

    private int GetPageIndex(HttpServletRequest request) {
        String value = request.getParameter("pc");
        if (value == null || value.isEmpty()) {
            return 1;
        }
        if (Integer.parseInt(value) < 1) {
            return 1;
        }
        return Integer.parseInt(value);
    }
}
