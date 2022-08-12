package com.data.en.decoder.entity.user;


import com.lz.mybatis.plugins.interceptor.annotation.Pull;
import lombok.Data;

import java.util.List;

@Data
public class UserPhoneDto extends UserPhone {


    @Pull(where = " user_id = #{id}  ", sort = "desc")
    private List<UserLogin> userLoginList;


}
