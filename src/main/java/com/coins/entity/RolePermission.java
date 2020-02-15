package com.coins.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Integer permissionId;

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
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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