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
* </p>*用户表
* @author quyixiao
* @since 2022-08-12
*/

@Data
@TableName("lt_user_phone")
public class UserPhone implements java.io.Serializable {
    //主键，自增id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //是否删除状态，1：删除，0：有效
    private Integer isDelete;
    //创建时间
    private Date gmtCreate;
    //最后修改时间
    private Date gmtModified;
    //用户名
    private String userNameEn;
    //姓名
    private String realNameEn;
    //用户身份唯一标识, '11位日期+5位(字符串+字母随机)  base64不可逆加密',
    private String uniqueCode;
    //渠道编码
    private String cnlCode;
    //用户标识 1:可借银行产品 2:可借小贷产品
    private Integer type;
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
     * 用户名 
     * @return
     */
    public String getUserNameEn() {
        return userNameEn;
    }
    /**
     * 用户名 
     * @param userNameEn
     */
    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }

    /**
     * 姓名 
     * @return
     */
    public String getRealNameEn() {
        return realNameEn;
    }
    /**
     * 姓名 
     * @param realNameEn
     */
    public void setRealNameEn(String realNameEn) {
        this.realNameEn = realNameEn;
    }

    /**
     * 用户身份唯一标识, '11位日期+5位(字符串+字母随机)  base64不可逆加密', 
     * @return
     */
    public String getUniqueCode() {
        return uniqueCode;
    }
    /**
     * 用户身份唯一标识, '11位日期+5位(字符串+字母随机)  base64不可逆加密', 
     * @param uniqueCode
     */
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    /**
     * 渠道编码 
     * @return
     */
    public String getCnlCode() {
        return cnlCode;
    }
    /**
     * 渠道编码 
     * @param cnlCode
     */
    public void setCnlCode(String cnlCode) {
        this.cnlCode = cnlCode;
    }

    /**
     * 用户标识 1:可借银行产品 2:可借小贷产品 
     * @return
     */
    public Integer getType() {
        return type;
    }
    /**
     * 用户标识 1:可借银行产品 2:可借小贷产品 
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserPhone{" +
                ",id=" + id +
                ",isDelete=" + isDelete +
                ",gmtCreate=" + gmtCreate +
                ",gmtModified=" + gmtModified +
                ",userNameEn=" + userNameEn +
                ",realNameEn=" + realNameEn +
                ",uniqueCode=" + uniqueCode +
                ",cnlCode=" + cnlCode +
                ",type=" + type +
                "}";
    }
}