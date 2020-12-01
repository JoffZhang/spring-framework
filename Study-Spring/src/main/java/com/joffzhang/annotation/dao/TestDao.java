package com.joffzhang.annotation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zy
 * @date 2020/11/30 11:04
 */
//名字默认是类名首字母小写
@Repository
public class TestDao {

	private String label = "1";

	@Override
	public String toString() {
		return "TestDao{" +
				"label='" + label + '\'' +
				'}';
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
