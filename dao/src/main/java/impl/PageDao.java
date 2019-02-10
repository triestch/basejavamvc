package impl;

import comuse.PageBean;
import db.DBHelper;
import idao.IPageDao;

public class PageDao<T> implements IPageDao<T> {

    public void queryPage(PageBean<T> pageBean,Class<T> entityClass) {
        DBHelper.queryPageProcedure(pageBean, entityClass);
    }



}
