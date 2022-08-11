package com.data.en.decoder.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

@FunctionalInterface
public interface ISelect {

    /**
     * 在接口中调用自己的查询方法，不要在该方法内写过多代码，只要一行查询方法最好
     */
    List doSelect(IPage page);

}
