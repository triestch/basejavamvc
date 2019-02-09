package impl;

import db.DBHelper;
import idao.IBaseDao;

import java.util.Map;

public abstract class  BaseDao<T> implements IBaseDao<T> {
    private String tbName;

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public boolean insertEntity(Map<String, Object> fieldMap)
    {
        return DBHelper.insertEntity(tbName,fieldMap);
    }

    public boolean updateEntity(String id, Map<String, Object> fieldMap)
    {
        return DBHelper.updateEntity(tbName,id,fieldMap);
    }

    public boolean fakedeleteEntity(String id)
    {
        return DBHelper.fakedeleteEntity(tbName,id);
    }

    public boolean deleteEntity(Object... params)
    {
        return DBHelper.deleteEntity(tbName,params);
    }
}
