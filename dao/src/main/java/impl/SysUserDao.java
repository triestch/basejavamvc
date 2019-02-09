package impl;

import databaseentity.SysUser;
import db.DBHelper;
import idao.ISysUserDao;

import java.util.List;
import java.util.Map;



/**
 * Sys系统平台用户表(SysUser)表数据库访问层
 *
 * @author triestch
 * @since 2019-02-09 16:14:34
 */
public class SysUserDao extends BaseDao<SysUser> implements ISysUserDao {

    private  String nowTbName="sys_user";
    public SysUserDao()
    {
        super.setTbName(nowTbName);
    }


    public List<SysUser> queryEntityList(String strWhere, Object... params) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("select * from "+nowTbName);
        if (strWhere.trim() != "") {
            strSql.append(" where " + strWhere);
        }
        return DBHelper.queryEntityList(SysUser.class, strSql.toString(), params);
    }

    public SysUser queryEntity(String strWhere, Object... params) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("select * from "+nowTbName);
        if (strWhere.trim() != "") {
            strSql.append(" where " + strWhere);
        }
        SysUser model=null;
        try {
            model=DBHelper.queryEntity(SysUser.class, strSql.toString(), params);
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        return model;
    }

    public List<Map<String, Object>> executeQuery(String strWhere, Object... params) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("select * from "+nowTbName);
        if (strWhere.trim() != "") {
            strSql.append(" where " + strWhere);
        }
        return DBHelper.executeQuery(strSql.toString(), params);
    }

}