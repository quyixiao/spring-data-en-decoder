package com.data.en.decoder.service.user;
/**
* <p>
* 用户登陆表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-12
*/
import com.baomidou.mybatisplus.extension.service.IService;
import com.data.en.decoder.entity.user.UserLogin;
public interface UserLoginService extends IService<UserLogin> {



	UserLogin selectUserLoginById(Long id);


	Long insertUserLogin(UserLogin userLogin);


	Long insertOrUpdateUserLogin(UserLogin userLogin);


	int updateUserLoginById(UserLogin userLogin);


	int updateCoverUserLoginById(UserLogin userLogin);


	int deleteUserLoginById(Long id);


}