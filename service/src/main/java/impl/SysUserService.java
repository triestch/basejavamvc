package impl;

import databaseentity.SysUser;
import iservice.ISysUserService;

/**
 * Sys系统平台用户表(SysUser)表业务层服务
 *
 * @author triestch
 * @since 2019-02-09 17:21:12
 */
public class SysUserService extends BaseService<SysUser> implements ISysUserService {

    public SysUserService()
    {
        SetCurrentDao();
    }

    public void SetCurrentDao() {
        CurrentDao = new SysUserDao();
    }

    public SysUser loginDeal(String name,String password)
    {
        Object[] params = {name, password};
        return CurrentDao.queryEntity("loginname=? and loginpwd=? and isdel=0 ", params);
    }
}