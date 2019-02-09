package impl;

import idao.IBaseDao;
import iservice.IBaseService;
import objdeal.EntityToMap;

import java.util.List;
import java.util.Map;

public abstract class BaseService<T> implements IBaseService<T> {

    public IBaseDao<T> CurrentDao = null;

    public abstract void SetCurrentDao();

    public List<T> queryEntityList(String sql, Object... params) {
        return CurrentDao.queryEntityList(sql, params);
    }

    public T queryEntity(String sql, Object... params) {

        return CurrentDao.queryEntity(sql, params);
    }

    public List<Map<String, Object>> executeQuery(String sql, Object... params) {
        return CurrentDao.executeQuery(sql, params);
    }

    public boolean insertEntity(T entityClass) {
        Map<String, Object> fieldMap = EntityToMap.toMap(entityClass);
        return CurrentDao.insertEntity(fieldMap);
    }

    public boolean updateEntity(T entityClass, String id) {
        Map<String, Object> fieldMap = EntityToMap.toMap(entityClass);
        return CurrentDao.updateEntity(id, fieldMap);
    }

    public boolean fakedeleteEntity(String id) {

        return CurrentDao.fakedeleteEntity(id);
    }

    public boolean deleteEntity(Object... params) {
        return CurrentDao.deleteEntity(params);
    }
}
