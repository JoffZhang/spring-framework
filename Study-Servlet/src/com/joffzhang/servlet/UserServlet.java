package com.joffzhang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2020/12/9 15:06
 */
public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserServlet start ,"+Thread.currentThread());
		try {
			sayHello();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		resp.getWriter().write("UserServlet...");
		System.out.println("UserServlet end ,"+Thread.currentThread());

	}

	public void sayHello() throws InterruptedException {
		System.out.println("UserServlet processing ,"+Thread.currentThread());
		TimeUnit.SECONDS.sleep(3);
	}
}
