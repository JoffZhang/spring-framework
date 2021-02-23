package com.joffzhang.lifecycle.context;

import com.joffzhang.PrintLog;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class AutoBean implements SmartLifecycle {

	boolean running;
	@Override
	public boolean isAutoStartup() {
		return true;
	}
	@Override
	public void start() {
		PrintLog.printNote("AutoBean  执行start方法");
		this.running = true;
	}

	@Override
	public void stop() {
		PrintLog.printNote("AutoBean  执行stop方法");
		this.running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}
}