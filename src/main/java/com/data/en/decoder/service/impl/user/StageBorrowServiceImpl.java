package com.data.en.decoder.service.impl.user;
/**
* <p>
* 分期借款表 服务类
* </p>
*
* @author quyixiao
* @since 2022-08-11
*/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.en.decoder.dao.user.StageBorrowDao;
import com.data.en.decoder.entity.user.StageBorrow;
import com.data.en.decoder.service.user.StageBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StageBorrowServiceImpl extends ServiceImpl<StageBorrowDao, StageBorrow> implements StageBorrowService {


    @Autowired
	private StageBorrowDao stageBorrowDao;



	@Override
	public StageBorrow selectStageBorrowById(Long id){
		return stageBorrowDao.selectStageBorrowById(id);
	}



	@Override
	public Long insertStageBorrow(StageBorrow stageBorrow){
		return stageBorrowDao.insertStageBorrow(stageBorrow);
	}



	@Override
	public Long insertOrUpdateStageBorrow(StageBorrow stageBorrow){
		return stageBorrowDao.insertOrUpdateStageBorrow(stageBorrow);
	}



	@Override
	public int updateStageBorrowById(StageBorrow stageBorrow){
		return stageBorrowDao.updateStageBorrowById(stageBorrow);
	}



	@Override
	public int updateCoverStageBorrowById(StageBorrow stageBorrow){
		return stageBorrowDao.updateCoverStageBorrowById(stageBorrow);
	}



	@Override
	public int deleteStageBorrowById(Long id){
		return stageBorrowDao.deleteStageBorrowById(id);
	}



}
