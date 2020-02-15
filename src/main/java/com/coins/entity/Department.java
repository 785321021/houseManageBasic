package com.coins.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "department")
public class Department implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 密码
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 部门级别
     */
    @Column(name = "`level`")
    private Integer level;

    /**
     * 父级部门id
     */
    private Integer pid;

    /**
     * 部门负责人id
     */
    @Column(name = "manager_id")
    private Integer managerId;

    /**
     * 部门负责人
     */
    @Column(name = "manager_name")
    private String managerName;

    /**
     * 创建人id
     */
    private Integer cid;

    /**
     * 创建人名称
     */
    private String cname;

    /**
     * 修改人id
     */
    private Integer mid;

    /**
     * 修改人名称
     */
    private String mname;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date mtime;

    /**
     * 是否删除
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名称
     *
     * @return name - 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称
     *
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return desc - 密码
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置密码
     *
     * @param desc 密码
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取部门级别
     *
     * @return level - 部门级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置部门级别
     *
     * @param level 部门级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取父级部门id
     *
     * @return pid - 父级部门id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级部门id
     *
     * @param pid 父级部门id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取部门负责人id
     *
     * @return manager_id - 部门负责人id
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * 设置部门负责人id
     *
     * @param managerId 部门负责人id
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取部门负责人
     *
     * @return manager_name - 部门负责人
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置部门负责人
     *
     * @param managerName 部门负责人
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * 获取创建人id
     *
     * @return cid - 创建人id
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置创建人id
     *
     * @param cid 创建人id
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取创建人名称
     *
     * @return cname - 创建人名称
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置创建人名称
     *
     * @param cname 创建人名称
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * 获取修改人id
     *
     * @return mid - 修改人id
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * 设置修改人id
     *
     * @param mid 修改人id
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * 获取修改人名称
     *
     * @return mname - 修改人名称
     */
    public String getMname() {
        return mname;
    }

    /**
     * 设置修改人名称
     *
     * @param mname 修改人名称
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取修改时间
     *
     * @return mtime - 修改时间
     */
    public Date getMtime() {
        return mtime;
    }

    /**
     * 设置修改时间
     *
     * @param mtime 修改时间
     */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取是否删除
     *
     * @return deleted - 是否删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted 是否删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}