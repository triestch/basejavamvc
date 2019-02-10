package impl;

import comuse.PageBean;
import idao.IPageDao;
import iservice.IPageService;

public class PageService<T> implements IPageService<T> {

    private IPageDao<T> CurrentDao = null;

    public PageService()
    {
        SetCurrentDao();
    }

    public void SetCurrentDao() {
        CurrentDao = new PageDao();
    }

    public void queryPage(PageBean<T> pageBean,Class<T> entityClass)
    {
        CurrentDao.queryPage(pageBean,entityClass);
    }


}
