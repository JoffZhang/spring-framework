package com.joffzhang.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zy
 * @date 2020/12/4 11:39
 */
@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	@Transactional
	public void insert(){
		int i= logDao.insert();
		//i = i/(i-1);
		System.out.println("插入完成"+i);
	}
}
