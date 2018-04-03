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
	    if (req.equals("doBioLookup.do"))
	    		view = request.getRequestDispatcher("bioresult.jsp");
	    else if (req.equals("doHist.do"))
	    		view = request.getRequestDispatcher("histogram.jsp");
	    else if (req.equals("doResult.do"))
    			view = request.getRequestDispatcher("result.jsp");
	    else if (req.equals("doMap.do"))
			view = request.getRequestDispatcher("map.jsp");
	    else if (req.equals("doCluster.do"))
			view = request.getRequestDispatcher("cluster.jsp");
	    
	    view.forward(request, response);
    }
	
	private String getUrlDoPortion(HttpServletRequest request) {
		String url = (String) request.getRequestURL().toString();
		String[] s = url.split("/");
		return s[s.length - 1];
	}
}