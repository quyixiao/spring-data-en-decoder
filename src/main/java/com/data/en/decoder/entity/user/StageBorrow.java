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
* </p>*分期借款表
* @author quyixiao
* @since 2022-08-12
*/

@Data
@TableName("lt_stage_borrow")
public class StageBorrow implements java.io.Serializable {
    //自增主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //是否删除
    private Long isDelete;
    //
    private Date gmtCreate;
    //
    private Date gmtModified;
    //用户唯一标识
    private String uniqueCode;
    /**
     * 自增主键 
     * @return
     */
    public Long getId() {
        return id;
    }
    /**
     * 自增主键 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 是否删除 
     * @return
     */
    public Long getIsDelete() {
        return isDelete;
    }
    /**
     * 是否删除 
     * @param isDelete
     */
    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    /**
     *  
     * @return
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    /**
     *  
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     *  
     * @return
     */
    public Date getGmtModified() {
        return gmtModified;
    }
    /**
     *  
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 用户唯一标识 
     * @return
     */
    public String getUniqueCode() {
        return uniqueCode;
    }
    /**
     * 用户唯一标识 
     * @param uniqueCode
     */
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    @Override
    public String toString() {
        return "StageBorrow{" +
                ",id=" + id +
                ",isDelete=" + isDelete +
                ",gmtCreate=" + gmtCreate +
                ",gmtModified=" + gmtModified +
                ",uniqueCode=" + uniqueCode +
                "}";
    }
}