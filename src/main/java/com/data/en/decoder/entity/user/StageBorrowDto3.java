package com.data.en.decoder.entity.user;


import com.lz.mybatis.plugins.interceptor.annotation.Pull;
import lombok.Data;

@Data
public class StageBorrowDto3 extends StageBorrow{

    @Pull(self = "uniqueCode", target = "uniqueCode", where = " type = 1 and cnl_code = 'chenn' ", sort = "desc",limit =  1 )
    private UserPhone userPhone;




}
