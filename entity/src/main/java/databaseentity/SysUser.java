package databaseentity;

import java.util.Date;
import java.io.Serializable;

/**
 * Sys系统平台用户表(SysUser)实体类
 *
 * @author triestch
 * @since 2019-02-09 15:19:33
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = -56555691680723033L;
    //主键ID
    private String pkid;
    //用户账号
    private String loginname;
    //登录口令
    private String loginpwd;
    //真实姓名
    private String truename;
    //性别（1-男，0-女，100无）
    private Integer sex;
    //部门ID（如果是企业则为企业信息ID）
    private String deptid;
    //部门名称(冗)
    private String deptname;
    //照片地址
    private String photopath;
    //办公电话
    private String officephone;
    //手机号码
    private String mobilephone;
    //电子邮箱
    private String email;
    //职务ID
    private String dutyid;
    //职务名称
    private String dutyname;
    //员工编号
    private String usercode;
    //角色ID（存储格式如：,12,13,14,）
    private String roleid;
    //证书内取出字符串（存储格式如：12^13,14）
    private String castrs;
    //用户类别：0-内部用户、1-企业注册入库、2-采购单位
    private Integer usertype;
    //业务类型(存储格式如：,12,13,14,类型代码 详见数据字典)
    private String biztype;
    //是否通过入库审核 0-否，1-是,2不需要审核，3再次提交修改中
    private Integer isstorage;
    //排序
    private Integer ordshow;
    //使用状态 0-停用，1-使用
    private Integer usestate;
    //备注
    private String remark;
    //创建人ID
    private String createrid;
    //创建人名称
    private String creatername;
    //创建时间
    private Date createtime;
    //更新人ID
    private String updaterid;
    //更新人名称
    private String updatername;
    //更新时间
    private Date updatetime;
    //是否删除 (1-是，0-否)
    private Integer isdel;
    //删除人ID
    private String delerid;
    //删除人名称
    private String delername;
    //删除时间
    private Date deltime;


    //获取主键ID    
    public String getPkid() {
        return pkid;
    }

    //设置主键ID
    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    //获取用户账号    
    public String getLoginname() {
        return loginname;
    }

    //设置用户账号
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    //获取登录口令    
    public String getLoginpwd() {
        return loginpwd;
    }

    //设置登录口令
    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    //获取真实姓名    
    public String getTruename() {
        return truename;
    }

    //设置真实姓名
    public void setTruename(String truename) {
        this.truename = truename;
    }

    //获取性别（1-男，0-女，100无）    
    public Integer getSex() {
        return sex;
    }

    //设置性别（1-男，0-女，100无）
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    //获取部门ID（如果是企业则为企业信息ID）    
    public String getDeptid() {
        return deptid;
    }

    //设置部门ID（如果是企业则为企业信息ID）
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    //获取部门名称(冗)    
    public String getDeptname() {
        return deptname;
    }

    //设置部门名称(冗)
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    //获取照片地址    
    public String getPhotopath() {
        return photopath;
    }

    //设置照片地址
    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    //获取办公电话    
    public String getOfficephone() {
        return officephone;
    }

    //设置办公电话
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    //获取手机号码    
    public String getMobilephone() {
        return mobilephone;
    }

    //设置手机号码
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    //获取电子邮箱    
    public String getEmail() {
        return email;
    }

    //设置电子邮箱
    public void setEmail(String email) {
        this.email = email;
    }

    //获取职务ID    
    public String getDutyid() {
        return dutyid;
    }

    //设置职务ID
    public void setDutyid(String dutyid) {
        this.dutyid = dutyid;
    }

    //获取职务名称    
    public String getDutyname() {
        return dutyname;
    }

    //设置职务名称
    public void setDutyname(String dutyname) {
        this.dutyname = dutyname;
    }

    //获取员工编号    
    public String getUsercode() {
        return usercode;
    }

    //设置员工编号
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    //获取角色ID（存储格式如：,12,13,14,）    
    public String getRoleid() {
        return roleid;
    }

    //设置角色ID（存储格式如：,12,13,14,）
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    //获取证书内取出字符串（存储格式如：12^13,14）    
    public String getCastrs() {
        return castrs;
    }

    //设置证书内取出字符串（存储格式如：12^13,14）
    public void setCastrs(String castrs) {
        this.castrs = castrs;
    }

    //获取用户类别：0-内部用户、1-企业注册入库、2-采购单位    
    public Integer getUsertype() {
        return usertype;
    }

    //设置用户类别：0-内部用户、1-企业注册入库、2-采购单位
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    //获取业务类型(存储格式如：,12,13,14,类型代码 详见数据字典)    
    public String getBiztype() {
        return biztype;
    }

    //设置业务类型(存储格式如：,12,13,14,类型代码 详见数据字典)
    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    //获取是否通过入库审核 0-否，1-是,2不需要审核，3再次提交修改中    
    public Integer getIsstorage() {
        return isstorage;
    }

    //设置是否通过入库审核 0-否，1-是,2不需要审核，3再次提交修改中
    public void setIsstorage(Integer isstorage) {
        this.isstorage = isstorage;
    }

    //获取排序    
    public Integer getOrdshow() {
        return ordshow;
    }

    //设置排序
    public void setOrdshow(Integer ordshow) {
        this.ordshow = ordshow;
    }

    //获取使用状态 0-停用，1-使用    
    public Integer getUsestate() {
        return usestate;
    }

    //设置使用状态 0-停用，1-使用
    public void setUsestate(Integer usestate) {
        this.usestate = usestate;
    }

    //获取备注    
    public String getRemark() {
        return remark;
    }

    //设置备注
    public void setRemark(String remark) {
        this.remark = remark;
    }

    //获取创建人ID    
    public String getCreaterid() {
        return createrid;
    }

    //设置创建人ID
    public void setCreaterid(String createrid) {
        this.createrid = createrid;
    }

    //获取创建人名称    
    public String getCreatername() {
        return creatername;
    }

    //设置创建人名称
    public void setCreatername(String creatername) {
        this.creatername = creatername;
    }

    //获取创建时间    
    public Date getCreatetime() {
        return createtime;
    }

    //设置创建时间
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    //获取更新人ID    
    public String getUpdaterid() {
        return updaterid;
    }

    //设置更新人ID
    public void setUpdaterid(String updaterid) {
        this.updaterid = updaterid;
    }

    //获取更新人名称    
    public String getUpdatername() {
        return updatername;
    }

    //设置更新人名称
    public void setUpdatername(String updatername) {
        this.updatername = updatername;
    }

    //获取更新时间    
    public Date getUpdatetime() {
        return updatetime;
    }

    //设置更新时间
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    //获取是否删除 (1-是，0-否)    
    public Integer getIsdel() {
        return isdel;
    }

    //设置是否删除 (1-是，0-否)
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    //获取删除人ID    
    public String getDelerid() {
        return delerid;
    }

    //设置删除人ID
    public void setDelerid(String delerid) {
        this.delerid = delerid;
    }

    //获取删除人名称    
    public String getDelername() {
        return delername;
    }

    //设置删除人名称
    public void setDelername(String delername) {
        this.delername = delername;
    }

    //获取删除时间    
    public Date getDeltime() {
        return deltime;
    }

    //设置删除时间
    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

}