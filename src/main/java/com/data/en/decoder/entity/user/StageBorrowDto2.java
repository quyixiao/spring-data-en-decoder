package com.data.en.decoder.entity.user;


import com.lz.mybatis.plugins.interceptor.annotation.Pull;
import lombok.Data;

import java.util.List;

@Data
public class StageBorrowDto2 extends StageBorrow{

    @Pull(self = "uniqueCode", target = "uniqueCode", sort = "desc",limit =  1 )
    private UserPhone userPhone;


}
