package com.joffzhang.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author zy
 * @date 2020/12/4 11:38
 */
@Repository
public class LogDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(){
		String sql = "INSERT INTO `tx_logger`.`t_logger`( `group_id`, `unit_id`, `tag`, `content`, `create_time`, `app_name`) VALUES ( ?, ?, ?,?, ?, ?);";
		String args = UUID.randomUUID().toString().substring(0,5);
		return jdbcTemplate.update(sql,args,args,args,args,args,args);
	}
}
