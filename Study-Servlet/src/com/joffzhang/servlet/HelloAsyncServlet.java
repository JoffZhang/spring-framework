package com.joffzhang.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 开启异步处理
 * @author zy
 * @date 2020/12/9 13:51
 */
@WebServlet(value = "/helloAsync",asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HelloAsyncServlet start ,"+Thread.currentThread());

		//1.支持异步处理asyncSupported =true
		//2.开启异步模式
		AsyncContext asyncContext = req.startAsync();
		//3.业务逻辑进行异步处理；开始异步处理
		asyncContext.start(() -> {
			try {
				System.out.println("HelloAsyncServlet  asyncContext.start(() -> {}) start ,"+Thread.currentThread());
				sayHello();
				//异步处理完成
				asyncContext.complete();
				//异步处理返回
				asyncContext.getResponse().getWriter().write("HelloAsyncServlet异步处理返回");
				System.out.println("HelloAsyncServlet asyncContext.start(() -> {}) end ,"+Thread.currentThread());

			} catch (InterruptedException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		System.out.println("HelloAsyncServlet end ,"+Thread.currentThread());

	}

	public void sayHello() throws InterruptedException {
		System.out.println("HelloAsyncServlet processing ,"+Thread.currentThread());
		TimeUnit.SECONDS.sleep(3);
	}
}
