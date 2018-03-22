package web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
//import com.example.model.TrawlExpert; // notice this
import model.*; // notice this


public class Director extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
	    String c = request.getParameter("phylum");

	    long time = (long)request.getServletContext().getAttribute("time");

	    request.setAttribute("time", time);
	    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
	    view.forward(request, response);
    }
}