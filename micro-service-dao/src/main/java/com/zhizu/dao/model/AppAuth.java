package com.zhizu.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "app_auth")
public class AppAuth implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 应用标识key
     */
    @Column(name = "app_key")
    private String appKey;

    /**
     * 加密算法secret
     */
    @Column(name = "app_secret")
    private String appSecret;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户申请时候的电话号码
     */
    private String phone;

    /**
     * 扩展参数 json
     */
    @Column(name = "ext_info")
    private String extInfo;

    /**
     * 是否删除
     */
    private Byte enabled;

    /**
     * 创建时间
     */
    @Column(name = "date_created")
    private Date dateCreated;

    /**
     * 更新时间
     */
    @Column(name = "date_updated")
    private Date dateUpdated;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取应用标识key
     *
     * @return app_key - 应用标识key
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置应用标识key
     *
     * @param appKey 应用标识key
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 获取加密算法secret
     *
     * @return app_secret - 加密算法secret
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * 设置加密算法secret
     *
     * @param appSecret 加密算法secret
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户申请时候的电话号码
     *
     * @return phone - 用户申请时候的电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户申请时候的电话号码
     *
     * @param phone 用户申请时候的电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取扩展参数 json
     *
     * @return ext_info - 扩展参数 json
     */
    public String getExtInfo() {
        return extInfo;
    }

    /**
     * 设置扩展参数 json
     *
     * @param extInfo 扩展参数 json
     */
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    /**
     * 获取是否删除
     *
     * @return enabled - 是否删除
     */
    public Byte getEnabled() {
        return enabled;
    }

    /**
     * 设置是否删除
     *
     * @param enabled 是否删除
     */
    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取创建时间
     *
     * @return date_created - 创建时间
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * 设置创建时间
     *
     * @param dateCreated 创建时间
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * 获取更新时间
     *
     * @return date_updated - 更新时间
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * 设置更新时间
     *
     * @param dateUpdated 更新时间
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appKey=").append(appKey);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", userId=").append(userId);
        sb.append(", phone=").append(phone);
        sb.append(", extInfo=").append(extInfo);
        sb.append(", enabled=").append(enabled);
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateUpdated=").append(dateUpdated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}