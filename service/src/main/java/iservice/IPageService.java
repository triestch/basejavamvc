package iservice;

import comuse.PageBean;

public interface IPageService<T> {
    void queryPage(PageBean<T> pageBean,Class<T> entityClass);
}
