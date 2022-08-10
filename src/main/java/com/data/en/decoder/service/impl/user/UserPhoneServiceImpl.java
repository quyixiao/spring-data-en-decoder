package com.data.en.decoder.service.impl.user;
/**
* <p>
* 用户表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-10
*/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.en.decoder.dao.user.UserPhoneDao;
import com.data.en.decoder.entity.user.UserPhone;
import com.data.en.decoder.service.user.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserPhoneServiceImpl extends ServiceImpl<UserPhoneDao, UserPhone> implements UserPhoneService {


    @Autowired
	private UserPhoneDao userPhoneDao;



	@Override
	public UserPhone selectUserPhoneById(Long id){
		return userPhoneDao.selectUserPhoneById(id);
	}



	@Override
	public Long insertUserPhone(UserPhone userPhone){
		return userPhoneDao.insertUserPhone(userPhone);
	}



	@Override
	public Long insertOrUpdateUserPhone(UserPhone userPhone){
		return userPhoneDao.insertOrUpdateUserPhone(userPhone);
	}



	@Override
	public int updateUserPhoneById(UserPhone userPhone){
		return userPhoneDao.updateUserPhoneById(userPhone);
	}



	@Override
	public int updateCoverUserPhoneById(UserPhone userPhone){
		return userPhoneDao.updateCoverUserPhoneById(userPhone);
	}



	@Override
	public int deleteUserPhoneById(Long id){
		return userPhoneDao.deleteUserPhoneById(id);
	}



}
