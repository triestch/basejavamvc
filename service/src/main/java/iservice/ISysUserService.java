package iservice;

import databaseentity.SysUser;


/**
 * Sys系统平台用户表(SysUser)表业务层接口
 *
 * @author triestch
 * @since 2019-02-09 16:30:06
 */
public interface ISysUserService extends IBaseService<SysUser> {
    SysUser loginDeal(String name,String password);
}