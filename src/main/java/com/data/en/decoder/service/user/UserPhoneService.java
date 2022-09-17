package com.data.en.decoder.service.user;
/**
* <p>
* 用户表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-10
*/
import com.baomidou.mybatisplus.extension.service.IService;
import com.data.en.decoder.entity.user.UserPhone;
public interface UserPhoneService extends IService<UserPhone> {



	UserPhone selectUserPhoneById(Long id);


	Long insertUserPhone(UserPhone userPhone);


	Long insertOrUpdateUserPhone(UserPhone userPhone);


	int updateUserPhoneById(UserPhone userPhone);


	int updateCoverUserPhoneById(UserPhone userPhone);


	int deleteUserPhoneById(Long id);


    void testLogInfo();
}