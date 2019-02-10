package idao;

import comuse.PageBean;

public interface IPageDao<T> {
    //public int getTotalRows();

    void queryPage(PageBean<T> pageBean,Class<T> entityClass);

}
