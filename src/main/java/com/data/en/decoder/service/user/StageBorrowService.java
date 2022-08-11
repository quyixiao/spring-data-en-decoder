package com.data.en.decoder.service.user;
/**
* <p>
* 分期借款表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-11
*/
import com.baomidou.mybatisplus.extension.service.IService;
import com.data.en.decoder.entity.user.StageBorrow;
public interface StageBorrowService extends IService<StageBorrow> {



	StageBorrow selectStageBorrowById(Long id);


	Long insertStageBorrow(StageBorrow stageBorrow);


	Long insertOrUpdateStageBorrow(StageBorrow stageBorrow);


	int updateStageBorrowById(StageBorrow stageBorrow);


	int updateCoverStageBorrowById(StageBorrow stageBorrow);


	int deleteStageBorrowById(Long id);


}