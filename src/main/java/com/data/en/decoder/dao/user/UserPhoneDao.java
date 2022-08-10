package com.data.en.decoder.dao.user;
/**
* <p>
* 用户表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-10
*/
import com.data.en.decoder.entity.user.UserPhone;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.mybatis.plugin.annotations.LIMIT;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserPhoneDao extends BaseMapper<UserPhone> {


	UserPhone selectUserPhoneById(@Param("id")Long id);


	Long insertUserPhone(UserPhone userPhone);


	Long insertOrUpdateUserPhone(UserPhone userPhone);


	int updateUserPhoneById(UserPhone userPhone);


	int updateCoverUserPhoneById(UserPhone userPhone);


	int deleteUserPhoneById(@Param("id")Long id);

	@LIMIT
	UserPhone selectUserPhoneByUserNameEn(String userNameEn);
}