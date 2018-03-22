package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//https://www.mkyong.com/servlet/what-is-listener-servletcontextlistener-example/
@WebListener
public class StartUpContext implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent serverEvent) {
		System.out.println("Started server.");
		serverEvent.getServletContext().setAttribute("time", (long) System.currentTimeMillis());
	}
}