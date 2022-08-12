package com.data.en.decoder.entity.user;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;import java.util.Date;
/**
* <p>
* 菜单权限表
* </p>*用户登陆表
* @author quyixiao
* @since 2022-08-12
*/

@Data
@TableName("lt_user_login")
public class UserLogin implements java.io.Serializable {
    //主键，自增id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //是否删除状态，1：删除，0：有效
    private Integer isDelete;
    //创建时间
    private Date gmtCreate;
    //最后修改时间
    private Date gmtModified;
    //用户id
    private Long userId;
    //登陆ip
    private String ip;
    //app版本
    private String appVersion;
    /**
     * 主键，自增id 
     * @return
     */
    public Long getId() {
        return id;
    }
    /**
     * 主键，自增id 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 是否删除状态，1：删除，0：有效 
     * @return
     */
    public Integer getIsDelete() {
        return isDelete;
    }
    /**
     * 是否删除状态，1：删除，0：有效 
     * @param isDelete
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建时间 
     * @return
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    /**
     * 创建时间 
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 最后修改时间 
     * @return
     */
    public Date getGmtModified() {
        return gmtModified;
    }
    /**
     * 最后修改时间 
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 用户id 
     * @return
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 用户id 
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 登陆ip 
     * @return
     */
    public String getIp() {
        return ip;
    }
    /**
     * 登陆ip 
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * app版本 
     * @return
     */
    public String getAppVersion() {
        return appVersion;
    }
    /**
     * app版本 
     * @param appVersion
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                ",id=" + id +
                ",isDelete=" + isDelete +
                ",gmtCreate=" + gmtCreate +
                ",gmtModified=" + gmtModified +
                ",userId=" + userId +
                ",ip=" + ip +
                ",appVersion=" + appVersion +
                "}";
    }
}