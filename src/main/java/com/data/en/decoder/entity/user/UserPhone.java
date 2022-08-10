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
* @since 2022-08-10
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
    //身份证号
    private String idNumber;
    //身份证号
    private String idNumberEn;
    //登录失败次数
    private Integer failCount;
    //版本号
    private Integer version;
    //用户身份唯一标识, '11位日期+5位(字符串+字母随机)  base64不可逆加密',
    private String uniqueCode;
    //是否是主卡 1: 是   0 :否
    private Integer isMain;
    //用户是否在用户池, 1:是, 0:否
    private Integer userPoolStatus;
    //沃钱包用户的用户编号
    private String userNo;
    //渠道编码
    private String cnlCode;
    //子渠道编号
    private String channelCode;
    //用户标识 1:可借银行产品 2:可借小贷产品
    private Integer type;
    //用户标记json类型, area用户区域, 0:不关心 1:黑龙江
    private String markJson;
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
     * 身份证号 
     * @return
     */
    public String getIdNumber() {
        return idNumber;
    }
    /**
     * 身份证号 
     * @param idNumber
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 身份证号 
     * @return
     */
    public String getIdNumberEn() {
        return idNumberEn;
    }
    /**
     * 身份证号 
     * @param idNumberEn
     */
    public void setIdNumberEn(String idNumberEn) {
        this.idNumberEn = idNumberEn;
    }

    /**
     * 登录失败次数 
     * @return
     */
    public Integer getFailCount() {
        return failCount;
    }
    /**
     * 登录失败次数 
     * @param failCount
     */
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    /**
     * 版本号 
     * @return
     */
    public Integer getVersion() {
        return version;
    }
    /**
     * 版本号 
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
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
     * 是否是主卡 1: 是   0 :否 
     * @return
     */
    public Integer getIsMain() {
        return isMain;
    }
    /**
     * 是否是主卡 1: 是   0 :否 
     * @param isMain
     */
    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    /**
     * 用户是否在用户池, 1:是, 0:否 
     * @return
     */
    public Integer getUserPoolStatus() {
        return userPoolStatus;
    }
    /**
     * 用户是否在用户池, 1:是, 0:否 
     * @param userPoolStatus
     */
    public void setUserPoolStatus(Integer userPoolStatus) {
        this.userPoolStatus = userPoolStatus;
    }

    /**
     * 沃钱包用户的用户编号 
     * @return
     */
    public String getUserNo() {
        return userNo;
    }
    /**
     * 沃钱包用户的用户编号 
     * @param userNo
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
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
     * 子渠道编号 
     * @return
     */
    public String getChannelCode() {
        return channelCode;
    }
    /**
     * 子渠道编号 
     * @param channelCode
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
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

    /**
     * 用户标记json类型, area用户区域, 0:不关心 1:黑龙江 
     * @return
     */
    public String getMarkJson() {
        return markJson;
    }
    /**
     * 用户标记json类型, area用户区域, 0:不关心 1:黑龙江 
     * @param markJson
     */
    public void setMarkJson(String markJson) {
        this.markJson = markJson;
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
                ",idNumber=" + idNumber +
                ",idNumberEn=" + idNumberEn +
                ",failCount=" + failCount +
                ",version=" + version +
                ",uniqueCode=" + uniqueCode +
                ",isMain=" + isMain +
                ",userPoolStatus=" + userPoolStatus +
                ",userNo=" + userNo +
                ",cnlCode=" + cnlCode +
                ",channelCode=" + channelCode +
                ",type=" + type +
                ",markJson=" + markJson +
                "}";
    }
}