package web;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Record;

import java.io.*;
import java.util.*;
//import com.example.model.TrawlExpert; // notice this
import model.*; // notice this
import search.BasicSearchResult;


public class Director extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher view = null;
		String req = getUrlDoPortion(request);
	    //if (req == "doBioLookup.do")
	    		view = request.getRequestDispatcher("bioresult.jsp");
	    		request.setAttribute("url", req);
	    
	    //RequestDispatcher view = request.getRequestDispatcher("histogram.jsp");
	    view.forward(request, response);
    }
	
	private String getUrlDoPortion(HttpServletRequest request) {
		String url = (String) request.getRequestURL().toString();
		String[] s = url.split("/");
		return s[s.length - 1];
	}
}