package com.data.en.decoder.dao.user;
/**
* <p>
* 用户登陆表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-12
*/
import com.data.en.decoder.entity.user.UserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserLoginDao extends BaseMapper<UserLogin> {


	UserLogin selectUserLoginById(@Param("id")Long id);


	Long insertUserLogin(UserLogin userLogin);


	Long insertOrUpdateUserLogin(UserLogin userLogin);


	int updateUserLoginById(UserLogin userLogin);


	int updateCoverUserLoginById(UserLogin userLogin);


	int deleteUserLoginById(@Param("id")Long id);


}