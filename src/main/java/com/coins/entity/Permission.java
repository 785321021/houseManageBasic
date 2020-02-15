package com.coins.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "permission")
public class Permission implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称 部门权限映射时角色前边必须为部门全名
     */
    @Column(name = "`action`")
    private String action;

    /**
     * 标题菜单权限的标题
     */
    private String title;

    /**
     * 描述
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 类型0菜单权限1功能权限
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 是否显示1显示0不显示
     */
    private Integer display;

    /**
     * 父级权限id
     */
    private Integer pid;

    /**
     * 排序数值越小排前边
     */
    private Integer sort;

    /**
     * 系统id
     */
    @Column(name = "app_id")
    private Integer appId;

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
     * 获取用户名称 部门权限映射时角色前边必须为部门全名
     *
     * @return action - 用户名称 部门权限映射时角色前边必须为部门全名
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置用户名称 部门权限映射时角色前边必须为部门全名
     *
     * @param action 用户名称 部门权限映射时角色前边必须为部门全名
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取标题菜单权限的标题
     *
     * @return title - 标题菜单权限的标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题菜单权限的标题
     *
     * @param title 标题菜单权限的标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取描述
     *
     * @return desc - 描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取类型0菜单权限1功能权限
     *
     * @return type - 类型0菜单权限1功能权限
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型0菜单权限1功能权限
     *
     * @param type 类型0菜单权限1功能权限
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取是否显示1显示0不显示
     *
     * @return display - 是否显示1显示0不显示
     */
    public Integer getDisplay() {
        return display;
    }

    /**
     * 设置是否显示1显示0不显示
     *
     * @param display 是否显示1显示0不显示
     */
    public void setDisplay(Integer display) {
        this.display = display;
    }

    /**
     * 获取父级权限id
     *
     * @return pid - 父级权限id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级权限id
     *
     * @param pid 父级权限id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取排序数值越小排前边
     *
     * @return sort - 排序数值越小排前边
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序数值越小排前边
     *
     * @param sort 排序数值越小排前边
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取系统id
     *
     * @return app_id - 系统id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置系统id
     *
     * @param appId 系统id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
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