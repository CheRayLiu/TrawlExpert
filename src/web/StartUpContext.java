package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.TrawlExpert;

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
		TrawlExpert te = new TrawlExpert();
		serverEvent.getServletContext().setAttribute("trawl", te);
	}
}