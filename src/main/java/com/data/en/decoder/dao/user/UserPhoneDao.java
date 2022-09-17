package com.data.en.decoder.dao.user;
/**
* <p>
* 用户表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-10
*/
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.data.en.decoder.entity.user.StageBorrow;
import com.data.en.decoder.entity.user.UserPhone;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.mybatis.plugin.annotations.IN;
import com.lz.mybatis.plugin.annotations.LIMIT;
import com.lz.mybatis.plugin.annotations.Row;
import com.lz.mybatis.plugins.interceptor.annotation.Bean2Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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


    List<UserPhone> selectUserPhoneByUserNameEns(  List<String> list);

	List<UserPhone> selectUserPhoneByUserNameEnEntitys(List<UserPhone> list);

	List<UserPhone> selectUserPhoneByArrayUserNameEns(String[] arrayxxx);

	List<UserPhone> selectUserPhoneByUserNameArrayEnEntitys(UserPhone[] arraysxxx);

	List<UserPhone> selectUserPhoneByArrayUserNameEnsAndRealName(@Param("arrays") String[] arrays,@Param("realNameEn") String realNameEn);

	UserPhone selectUserPhoneByUsernNameObject(UserPhone userPhone);

	List<UserPhone> selectUserPhoneByPageUserNameEns(IPage page,@IN List<String> userNameEn);
	List<UserPhone> selectUserPhoneByUniqueCodes(@IN List<String> uniqueCode);


	@LIMIT
	UserPhone selectUserPhoneByUniqueCode(String uniqueCode);


	@Bean2Map(key = "uniqueCode",value = "this",fillNull = false)
	Map<String, UserPhone> selectUserPhoneMapByUniqueCode(@IN  @Row("uniqueCode") List<StageBorrow> uniqueCode);


	@Bean2Map(key = "uniqueCode",value = "realNameEn")
	Map<String, String> selectUserPhoneMapByUniqueCodeRealName(@IN("uniqueCode")  @Row("uniqueCode")List<StageBorrow> borrows);
}