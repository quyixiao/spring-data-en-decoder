package com.data.en.decoder.service.impl.user;
/**
* <p>
* 用户登陆表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-12
*/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.en.decoder.dao.user.UserLoginDao;
import com.data.en.decoder.entity.user.UserLogin;
import com.data.en.decoder.service.user.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginDao, UserLogin> implements UserLoginService {


    @Autowired
	private UserLoginDao userLoginDao;



	@Override
	public UserLogin selectUserLoginById(Long id){
		return userLoginDao.selectUserLoginById(id);
	}



	@Override
	public Long insertUserLogin(UserLogin userLogin){
		return userLoginDao.insertUserLogin(userLogin);
	}



	@Override
	public Long insertOrUpdateUserLogin(UserLogin userLogin){
		return userLoginDao.insertOrUpdateUserLogin(userLogin);
	}



	@Override
	public int updateUserLoginById(UserLogin userLogin){
		return userLoginDao.updateUserLoginById(userLogin);
	}



	@Override
	public int updateCoverUserLoginById(UserLogin userLogin){
		return userLoginDao.updateCoverUserLoginById(userLogin);
	}



	@Override
	public int deleteUserLoginById(Long id){
		return userLoginDao.deleteUserLoginById(id);
	}



}
