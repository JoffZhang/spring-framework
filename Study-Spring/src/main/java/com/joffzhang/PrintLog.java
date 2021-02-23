package com.joffzhang;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zy
 * @date 2021/1/26 15:51
 */
public class PrintLog {
	private static Map<String,Logger> logMap = new HashMap<>();
	/**
	 * 打印标记
	 * @param primary
	 * @param secondary
	 */
	public static void printMark(String primary,String secondary){
		getLog().info(" 【{}】 {}",primary,secondary);
	}


	/**
	 * 打印笔记注释
	 * @param msg
	 */
	public static void printNote(String msg){
		getLog().trace("  |-> {}" , msg);
	}

	/**
	 * 打印例子
	 * @param caseMsg
	 */
	public static void printCase(String caseMsg){
		getLog().debug("   |**  case  **  {}" ,caseMsg);
	}


	private static Logger getLog() {
		String clazz = defaultClass();
		if(!logMap.containsKey(clazz)){
			Logger logger = LoggerFactory.getLogger(clazz);
			logMap.put(clazz,logger );
			return logger;
		}
		return logMap.get(clazz);
	}
	/*
	java.lang.Thread	getStackTrace	1559
	com.joffzhang.PrintLog	defaultClass	38
	com.joffzhang.PrintLog	printCase	35
	com.joffzhang.lifecycle.bean.DemoBean	destroy	65
	org.springframework.beans.factory.support.DisposableBeanAdapter	destroy	258
	 */
	private static String defaultClass(){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement log = stackTrace[1];
		String defaultClass = "*";
//		for (int i = 1; i < stackTrace.length; i++) {
//			StackTraceElement e = stackTrace[i];
//			if(!e.getClassName().equals(log.getClassName())){
//				defaultClass = e.getClassName();
//				break;
//			}
//		}
		return defaultClass;
	}

}
