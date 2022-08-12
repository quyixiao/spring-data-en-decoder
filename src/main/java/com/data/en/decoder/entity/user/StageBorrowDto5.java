package com.data.en.decoder.entity.user;


import com.lz.mybatis.plugins.interceptor.annotation.Pull;
import lombok.Data;

@Data
public class StageBorrowDto5 extends StageBorrow{

    @Pull(where = " unique_code = #{uniqueCode} and  type = 1 and cnl_code = 'chenn' ", sort = "desc",limit =  1 )
    private UserPhoneDto userPhone;




}
