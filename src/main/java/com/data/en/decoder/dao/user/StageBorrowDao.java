package com.data.en.decoder.dao.user;
/**
* <p>
* 分期借款表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-11
*/
import com.data.en.decoder.entity.user.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StageBorrowDao extends BaseMapper<StageBorrow> {


	StageBorrow selectStageBorrowById(@Param("id")Long id);


	Long insertStageBorrow(StageBorrow stageBorrow);


	Long insertOrUpdateStageBorrow(StageBorrow stageBorrow);


	int updateStageBorrowById(StageBorrow stageBorrow);


	int updateCoverStageBorrowById(StageBorrow stageBorrow);


	int deleteStageBorrowById(@Param("id")Long id);


	List<StageBorrow> selectStageBorrowByAll();

	List<StageBorrowDto> selectStageBorrowByAllDto();

	List<StageBorrowDto2> selectStageBorrowByAllDto2();

	List<StageBorrowDto3> selectStageBorrowByAllDto3();

	List<StageBorrowDto4> selectStageBorrowByAllDto4();
}